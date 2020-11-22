/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import java.sql.*;
import Classes.ModuloConexao;
import javax.swing.JOptionPane;
public class TelaUsuario extends javax.swing.JInternalFrame {

        Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
        
        
    public TelaUsuario() {
        initComponents();
        
        
        conexao = ModuloConexao.conector();
    }
    
    private void adicionar(){
    String sql = "insert into tbusers (idUser,usuario,fone,login,senha,perfil) values (?,?,?,?,?,?)";
    try{
        
        pst = conexao.prepareStatement(sql);
        pst.setString(1,txtID.getText());
        pst.setString(2,txtNome.getText());
        pst.setString(3,txtFone.getText());
        pst.setString(4,txtLogin.getText());
        pst.setString(5,txtSenha.getText());
        pst.setString(6,optionPerfil.getSelectedItem().toString());
        
        
        if(txtID.getText().isEmpty() || txtNome.getText().isEmpty()|| txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty() || optionPerfil.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null, "Preencha todos os itens obrigatórios.");
        }else{
        int add = pst.executeUpdate();
        if(add > 0){
         
        JOptionPane.showMessageDialog(null, "Usuário "+this.txtNome.getText()+" cadastrado com sucesso.");
                txtID.setText(null);
                txtNome.setText(null);
                txtFone.setText(null);
                txtLogin.setText(null);
                txtSenha.setText(null);
               
        }  
     }   
        
}catch(Exception e){
    JOptionPane.showMessageDialog(null,e);
}
    }
    private void consultar(){
        String sql = "select * from tbusers where idUser=?";
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtID.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNome.setText(rs.getString(2));
                txtFone.setText(rs.getString(3));
                txtLogin.setText(rs.getString(4));
                txtSenha.setText(rs.getString(5));
                optionPerfil.setSelectedItem(rs.getString(6));
                
                
            } else {
                txtNome.setText(null);
                txtFone.setText(null);
                txtLogin.setText(null);
                txtSenha.setText(null);
                optionPerfil.setSelectedItem(null);
                JOptionPane.showMessageDialog(null, "Nada encontrado.");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void editar(){
        String sql = "update tbusers set usuario=?,fone=?,login=?,senha=?,perfil=? where idUser =?";
        
        
        try{
            
            pst = conexao.prepareStatement(sql);
            
            pst.setString(1,txtNome.getText());
            pst.setString(2,txtFone.getText());
            pst.setString(3,txtLogin.getText());
            pst.setString(4,txtSenha.getText());
            pst.setString(5,txtNome.getText());
            pst.setString(6,txtID.getText());
            
            
            if (txtID.getText().isEmpty() || txtNome.getText().isEmpty() || txtLogin.getText().isEmpty() || txtSenha.getText().isEmpty() || optionPerfil.getSelectedItem().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha todos os itens obrigatórios.");
            } else {
                
                int add = pst.executeUpdate();
                
                if (add > 0) {

                    JOptionPane.showMessageDialog(null, "Usuário editado com sucesso.");
                    txtID.setText(null);
                    txtNome.setText(null);
                    txtFone.setText(null);
                    txtLogin.setText(null);
                    txtSenha.setText(null);
        
        
        }
               
        } 
            
        }catch(Exception e){
        
        JOptionPane.showMessageDialog(null, e);
        
        }
    
    }
    
    
    public void apagar(){
   
    
            
            
     int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
     
     if(confirmar == JOptionPane.YES_OPTION){
         
        String sql = "delete from tbusers where idUser=?";
        try{
           pst = conexao.prepareStatement(sql);
           
           pst.setString(1, txtID.getText());
           int apagado = pst.executeUpdate();
           if(apagado >0){
            JOptionPane.showMessageDialog(null, "Usuário apagado com sucesso.");
            txtID.setText(null);
            txtNome.setText(null);
            txtFone.setText(null);
            txtLogin.setText(null);
            txtSenha.setText(null);
           
           }
           
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

        lblID = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblLogin = new javax.swing.JLabel();
        lblSenha = new javax.swing.JLabel();
        lblPerfil = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        optionPerfil = new javax.swing.JComboBox<>();
        lblFone = new javax.swing.JLabel();
        txtFone = new javax.swing.JTextField();
        btnApagar = new javax.swing.JButton();
        btnCriar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtSenha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");

        lblID.setText("* Id");

        lblNome.setText("* Nome:");

        lblLogin.setText("* Login:");

        lblSenha.setText("* Senha:");

        lblPerfil.setText("*  Perfil:");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        optionPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "user", "admin", " ", " " }));
        optionPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionPerfilActionPerformed(evt);
            }
        });

        lblFone.setText("Fone:");

        btnApagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        btnApagar.setToolTipText("Apagar");
        btnApagar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        btnCriar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/add.png"))); // NOI18N
        btnCriar.setToolTipText("Adicionar");
        btnCriar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCriarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/edit.png"))); // NOI18N
        btnEditar.setToolTipText("Editar");
        btnEditar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/search.png"))); // NOI18N
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.setPreferredSize(new java.awt.Dimension(95, 95));
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSenhaActionPerformed(evt);
            }
        });

        jLabel1.setText("* Campos obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblID)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblFone)
                                    .addComponent(lblNome)
                                    .addComponent(lblPerfil))
                                .addGap(260, 260, 260)
                                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(lblSenha))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(lblLogin)
                        .addGap(18, 18, 18)
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                    .addComponent(txtFone, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(optionPerfil, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCriar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(332, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(122, 122, 122))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnApagar, btnBuscar, btnCriar, btnEditar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblID))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogin))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFone)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSenha)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPerfil)
                    .addComponent(optionPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCriar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(247, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnApagar, btnBuscar, btnCriar, btnEditar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void optionPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_optionPerfilActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        consultar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void btnCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCriarActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnCriarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        // TODO add your handling code here:
        apagar();
    }//GEN-LAST:event_btnApagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCriar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblFone;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblPerfil;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JComboBox<String> optionPerfil;
    private javax.swing.JTextField txtFone;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtSenha;
    // End of variables declaration//GEN-END:variables
}
