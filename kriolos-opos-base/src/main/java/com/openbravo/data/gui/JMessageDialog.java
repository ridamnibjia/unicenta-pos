//    KrOS POS  - Open Source Point Of Sale
//    Copyright (c) 2009-2018 uniCenta & previous Openbravo POS works
//
//    This program is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    This program is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with this program.  If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.data.gui;

import com.openbravo.data.loader.LocalRes;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author adrian
 */
public class JMessageDialog extends javax.swing.JDialog {

    private int optionChosed = Integer.MIN_VALUE;

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form JMessageDialog
     */
    private JMessageDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    /**
     * Creates new form JMessageDialog
     */
    private JMessageDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
    }

    public int getOptionChosed() {
        return optionChosed;
    }

    private static Window getWindow(Component parent) {
        if (parent == null) {
            return new JFrame();
        } else if (parent instanceof Frame || parent instanceof Dialog) {
            return (Window) parent;
        } else {
            return getWindow(parent.getParent());
        }
    }

    /**
     *
     * @param parent
     * @param inf
     */
    public static void showMessage(Component parent, MessageInf inf) {

        createMessageDialog(parent, inf, false);
    }

    public static int showConfirmDialog(Component parent, MessageInf inf) {
        return createMessageDialog(parent, inf, true);
    }

    private static int createMessageDialog(Component parent, MessageInf inf, boolean showConfirm) {
        Window window = getWindow(parent);
        JMessageDialog myMsg;
        if (window instanceof Frame) {
            myMsg = new JMessageDialog((Frame) window, true);
        } else {
            myMsg = new JMessageDialog((Dialog) window, true);
        }

        myMsg.initComponents();
        myMsg.applyComponentOrientation(parent.getComponentOrientation());
        myMsg.jscrException.setVisible(false);
        myMsg.getRootPane().setDefaultButton(myMsg.jcmdOK);

        myMsg.jlblIcon.setIcon(inf.getSignalWordIcon());
        myMsg.jlblIcon.setText(inf.getCode() +" "+ inf.getErrorCodeMsg());
        myMsg.jlblMessage.setText(inf.getMessage());
        
        myMsg.jcmdCancel.setEnabled(showConfirm);
        myMsg.jcmdCancel.setVisible(showConfirm);

        // Capturamos el texto de la excepcion...
        if (inf.getCause() == null) {
            myMsg.jtxtException.setText(null);
        } else {
            StringBuilder sb = new StringBuilder();

            if (inf.getCause() instanceof Throwable) {
                Throwable t = (Throwable) inf.getCause();
                while (t != null) {
                    sb.append(t.getClass().getName());
                    sb.append(": \n");
                    sb.append(t.getMessage());
                    sb.append("\n\n");
                    t = t.getCause();
                }
            } else if (inf.getCause() instanceof Throwable[]) {
                Throwable[] m_aExceptions = (Throwable[]) inf.getCause();
                for (int i = 0; i < m_aExceptions.length; i++) {
                    sb.append(m_aExceptions[i].getClass().getName());
                    sb.append(": \n");
                    sb.append(m_aExceptions[i].getMessage());
                    sb.append("\n\n");
                }
            } else if (inf.getCause() instanceof Object[]) {
                Object[] m_aObjects = (Object[]) inf.getCause();
                for (int i = 0; i < m_aObjects.length; i++) {
                    sb.append(m_aObjects[i].toString());
                    sb.append("\n\n");
                }
            } else if (inf.getCause() instanceof String) {
                sb.append(inf.getCause().toString());
            } else {
                sb.append(inf.getCause().getClass().getName());
                sb.append(": \n");
                sb.append(inf.getCause().toString());
            }
            myMsg.jtxtException.setText(sb.toString());
        }
        myMsg.jtxtException.setCaretPosition(0);
        myMsg.setVisible(true);
        return myMsg.getOptionChosed();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jlblIcon = new javax.swing.JLabel();
        jlblMessage = new javax.swing.JLabel();
        jscrException = new javax.swing.JScrollPane();
        jtxtException = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jcmdCancel = new javax.swing.JButton();
        jcmdOK = new javax.swing.JButton();
        jcmdMore = new javax.swing.JButton();

        setTitle(LocalRes.getIntString("title.message")); // NOI18N
        setMaximumSize(new java.awt.Dimension(480, 300));
        setMinimumSize(new java.awt.Dimension(300, 200));
        setPreferredSize(new java.awt.Dimension(260, 277));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        jPanel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jlblIcon.setText("jlblIcon");
        jlblIcon.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jlblIcon.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanel4.add(jlblIcon);

        jlblMessage.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jlblMessage.setText("jlblMessage");
        jlblMessage.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jlblMessage.setMaximumSize(new java.awt.Dimension(460, 90));
        jlblMessage.setMinimumSize(new java.awt.Dimension(200, 30));
        jlblMessage.setPreferredSize(new java.awt.Dimension(200, 64));
        jPanel4.add(jlblMessage);

        jscrException.setAlignmentX(0.0F);
        jscrException.setPreferredSize(new java.awt.Dimension(200, 150));

        jtxtException.setEditable(false);
        jtxtException.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jscrException.setViewportView(jtxtException);

        jPanel4.add(jscrException);

        getContentPane().add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel3.setPreferredSize(new java.awt.Dimension(200, 50));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(260, 50));

        jcmdCancel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcmdCancel.setText(LocalRes.getIntString("button.cancel"));
        jcmdCancel.setMaximumSize(new java.awt.Dimension(80, 42));
        jcmdCancel.setMinimumSize(new java.awt.Dimension(80, 42));
        jcmdCancel.setPreferredSize(new java.awt.Dimension(80, 42));
        jcmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdCancelActionPerformed(evt);
            }
        });
        jPanel2.add(jcmdCancel);

        jcmdOK.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcmdOK.setText(LocalRes.getIntString("button.ok"));
        jcmdOK.setMaximumSize(new java.awt.Dimension(80, 42));
        jcmdOK.setMinimumSize(new java.awt.Dimension(80, 42));
        jcmdOK.setPreferredSize(new java.awt.Dimension(80, 42));
        jcmdOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdOKActionPerformed(evt);
            }
        });
        jPanel2.add(jcmdOK);

        jcmdMore.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jcmdMore.setText(LocalRes.getIntString("button.information"));
        jcmdMore.setMaximumSize(new java.awt.Dimension(80, 42));
        jcmdMore.setMinimumSize(new java.awt.Dimension(80, 42));
        jcmdMore.setPreferredSize(new java.awt.Dimension(80, 42));
        jcmdMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcmdMoreActionPerformed(evt);
            }
        });
        jPanel2.add(jcmdMore);

        jPanel3.add(jPanel2, java.awt.BorderLayout.LINE_END);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        setSize(new java.awt.Dimension(482, 305));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jcmdMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdMoreActionPerformed

        jcmdMore.setEnabled(false);
        jscrException.setVisible(true);
        setSize(getWidth(), 310);
        validate();

    }//GEN-LAST:event_jcmdMoreActionPerformed

    private void jcmdOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdOKActionPerformed
        this.optionChosed = 0;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jcmdOKActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        optionChosed = -1;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jcmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcmdCancelActionPerformed
        // TODO add your handling code here:
        this.optionChosed = -1;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_jcmdCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jcmdCancel;
    private javax.swing.JButton jcmdMore;
    private javax.swing.JButton jcmdOK;
    private javax.swing.JLabel jlblIcon;
    private javax.swing.JLabel jlblMessage;
    private javax.swing.JScrollPane jscrException;
    private javax.swing.JTextArea jtxtException;
    // End of variables declaration//GEN-END:variables

}
