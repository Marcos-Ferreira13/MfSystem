/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import Classes.ModuloConexao;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    
    PreparedStatement pst = null;
    ResultSet rs = null;
    
     private String tipo;
     
    public TelaOS() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    
    private void consultar(){
        
        String sql = "select idCli as id, nomeCli as Nome,foneCli as Fone from tbcliente where nomeCli like ?";
     
     try{
         pst = conexao.prepareStatement(sql);
         pst.setString(1, ClienteOS.getText()+ "%");
         rs = pst.executeQuery();
         
         tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
         
     }catch(Exception e){
         JOptionPane.showMessageDialog(null, e);
     }
     
    
    
    }
    
    
    private void setarTabela(){
    int setar = tblClientes.getSelectedRow();
    
    idOS.setText(tblClientes.getModel().getValueAt(setar,0).toString());
    
    }
    
    private void emitir_OS(){
    String sql = "insert into ordemservice (tipo,situacao,equipamento,defeito,servico,tecnico,valor,idCli) values (?,?,?,?,?,?,?,?)";
    try{
       pst = conexao.prepareStatement(sql);
       pst.setString(1, tipo);
       pst.setString(2, chooseSituacao.getSelectedItem().toString());
       pst.setString(3, txtEquip.getText());
       pst.setString(4, txtDefeito.getText());
       pst.setString(5, txtServico.getText());
       pst.setString(6, txtTecnico.getText());
       pst.setString(7, txtValor.getText());
       pst.setString(8, idOS.getText());
       
        if (idOS.getText().isEmpty() || txtEquip.getText().isEmpty() || txtDefeito.getText().isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
        } else {
            int add = pst.executeUpdate();
            if(add>0){
            JOptionPane.showMessageDialog(null, "Ordem de serviço emitida com sucesso.");
            idOS.setText(null);
            txtEquip.setText(null);
            txtDefeito.setText(null);
            txtServico.setText(null);
            txtTecnico.setText(null);
            txtValor.setText(null);
            
            }
        }
       
    }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void procurar_OS(){
        String num_OS = JOptionPane.showInputDialog("Número da OS: ");
        
        String sql = "select * from ordemservice where os= "+ num_OS;
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                txtOS.setText(rs.getString(1));
                dataOS.setText(rs.getString(2));
                String retTipo = rs.getString(3);
             
                if(retTipo.equals("Ord.Serviço")){
                btnOS.setSelected(true);
                tipo = "OS";
                }else{
                
                btnORC.setSelected(true);
                tipo = "Orçamento";
                }
                chooseSituacao.setSelectedItem(rs.getString(4));
                txtEquip.setText(rs.getString(5));
                txtDefeito.setText(rs.getString(6));
                txtServico.setText(rs.getString(7));
                txtTecnico.setText(rs.getString(8));
                txtValor.setText(rs.getString(9).replace(",", ","));
                idOS.setText(rs.getString(10));
                
                btnAdicionar.setEnabled(false);
                ClienteOS.setEnabled(false);
                tblClientes.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "OS não encontrada.");
            }
        }catch(Exception e){
        
        
        }
    }
    
    private void alterar_OS(){
    
    String sql = "update ordemservice set tipo=?,situacao=?,equipamento=?,defeito=?,servico=?,tecnico=?,valor=? where os=?";
    
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, chooseSituacao.getSelectedItem().toString());
            pst.setString(3, txtEquip.getText());
            pst.setString(4, txtDefeito.getText());
            pst.setString(5, txtServico.getText());
            pst.setString(6, txtTecnico.getText());
            pst.setString(7, txtValor.getText().replace(",", ","));
            pst.setString(8, idOS.getText());

            if (idOS.getText().isEmpty() || txtEquip.getText().isEmpty() || txtDefeito.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios.");
            } else {
                int add = pst.executeUpdate();
                if (add > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de serviço alterada com sucesso.");
                    txtOS.setText(null);
                    dataOS.setText(null);
                    idOS.setText(null);
                    txtEquip.setText(null);
                    txtDefeito.setText(null);
                    txtServico.setText(null);
                    txtTecnico.setText(null);
                    txtValor.setText(null);
                    
                    btnAdicionar.setEnabled(true);
                    tblClientes.setVisible(true);
                    ClienteOS.setEnabled(true);

                }
                
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void deletar_OS(){
    int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
    
    if(confirmar == JOptionPane.YES_OPTION){
    
    String sql = "delete from ordemservice where os=?";
    
    try{
        
        pst = conexao.prepareStatement(sql);
        pst.setString(1, txtOS.getText());
        
        int apagado = pst.executeUpdate();
        
        if(apagado>0){
        
        JOptionPane.showMessageDialog(null, "Apagado com sucesso.");
        txtOS.setText(null);
                    dataOS.setText(null);
                    idOS.setText(null);
                    txtEquip.setText(null);
                    txtDefeito.setText(null);
                    txtServico.setText(null);
                    txtTecnico.setText(null);
                    txtValor.setText(null);
                    
                    btnAdicionar.setEnabled(true);
                    tblClientes.setVisible(true);
                    ClienteOS.setEnabled(true);
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        
     }
    
    
    }
    
    
    }
    
    
    private void imprimir_OS(){
    int confirmar = JOptionPane.showConfirmDialog(null , "Deseja realizar a impressão desse relatório?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        
        if(confirmar == JOptionPane.YES_OPTION){
        
        try{
                HashMap filtro = new HashMap();
                filtro.put("os",Integer.parseInt(txtOS.getText()));
                
                
            JasperPrint print = JasperFillManager.fillReport("D:\\ireport\\OS.jasper",filtro,conexao);
            
            JasperViewer.viewReport(print,false);
        
        }catch(Exception e){
        
        
        JOptionPane.showMessageDialog(null, e);
        
        }
        
        }
    
    
    
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOS = new javax.swing.JTextField();
        dataOS = new javax.swing.JTextField();
        btnORC = new javax.swing.JRadioButton();
        btnOS = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        chooseSituacao = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        ClienteOS = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        idOS = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEquip = new javax.swing.JTextField();
        txtTecnico = new javax.swing.JTextField();
        txtDefeito = new javax.swing.JTextField();
        txtServico = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtValor = new javax.swing.JTextField();
        btnAdicionar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnApagar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de serviço");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("N° Ordem Serviço");

        jLabel2.setText("Data");

        txtOS.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        txtOS.setEnabled(false);
        txtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOSActionPerformed(evt);
            }
        });

        dataOS.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        dataOS.setEnabled(false);
        dataOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataOSActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnORC);
        btnORC.setText("Orçamento");
        btnORC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnORCActionPerformed(evt);
            }
        });

        buttonGroup1.add(btnOS);
        btnOS.setText("Ord.Serviço");
        btnOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(97, 97, 97))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dataOS, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnORC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOS)
                        .addGap(45, 45, 45))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dataOS, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnORC)
                    .addComponent(btnOS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Situação:");

        chooseSituacao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na bancada", "Situação OK", "Orçamento aprovado", "orçamento reprovado", "Aguardando peças", "Abandonado pelo cliente", "Retornou" }));
        chooseSituacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseSituacaoActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        ClienteOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteOSActionPerformed(evt);
            }
        });
        ClienteOS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ClienteOSKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/searchC.png"))); // NOI18N

        jLabel5.setText("* id");

        idOS.setEditable(false);
        idOS.setEnabled(false);
        idOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idOSActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Fone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(ClienteOS, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(32, 32, 32)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idOS, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 97, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ClienteOS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(idOS, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setText("* Equipamento:");

        jLabel7.setText("* Defeito:");

        jLabel8.setText("Serviço: ");

        jLabel9.setText("Técnico:");

        txtEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEquipActionPerformed(evt);
            }
        });

        txtTecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTecnicoActionPerformed(evt);
            }
        });

        txtDefeito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDefeitoActionPerformed(evt);
            }
        });

        jLabel10.setText("Valor:");

        txtValor.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(574, 574, 574))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(txtDefeito, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(32, 32, 32)
                                .addComponent(txtEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtValor))
                                .addComponent(txtServico, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(txtEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtDefeito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(jLabel8))
                    .addComponent(txtServico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTecnico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/add.png"))); // NOI18N
        btnAdicionar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/search.png"))); // NOI18N
        btnBuscar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/edit.png"))); // NOI18N
        btnEditar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        btnApagar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/printer.png"))); // NOI18N
        btnImprimir.setPreferredSize(new java.awt.Dimension(95, 95));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(59, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(chooseSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
            .addGroup(layout.createSequentialGroup()
                .addGap(184, 184, 184)
                .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(chooseSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        setBounds(0, 0, 979, 682);
    }// </editor-fold>//GEN-END:initComponents

    private void dataOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dataOSActionPerformed

    private void chooseSituacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseSituacaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chooseSituacaoActionPerformed

    private void ClienteOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ClienteOSActionPerformed

    private void txtEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEquipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEquipActionPerformed

    private void txtTecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTecnicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTecnicoActionPerformed

    private void ClienteOSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ClienteOSKeyReleased
        consultar();
    }//GEN-LAST:event_ClienteOSKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        setarTabela();       
    }//GEN-LAST:event_tblClientesMouseClicked

    private void idOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idOSActionPerformed

    private void btnORCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnORCActionPerformed
       tipo = "Orçamento";
    }//GEN-LAST:event_btnORCActionPerformed

    private void btnOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSActionPerformed
        tipo = "Ord.Serviço";
        
    }//GEN-LAST:event_btnOSActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        btnORC.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
              emitir_OS();
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       procurar_OS();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOSActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        alterar_OS();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        deletar_OS();
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimir_OS();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtDefeitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDefeitoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDefeitoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ClienteOS;
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JRadioButton btnORC;
    private javax.swing.JRadioButton btnOS;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> chooseSituacao;
    private javax.swing.JTextField dataOS;
    private javax.swing.JTextField idOS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtDefeito;
    private javax.swing.JTextField txtEquip;
    private javax.swing.JTextField txtOS;
    private javax.swing.JTextField txtServico;
    private javax.swing.JTextField txtTecnico;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
