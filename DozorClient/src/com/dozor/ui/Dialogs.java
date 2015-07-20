package com.dozor.ui;

import com.dozor.langs.LocaleBundle;
import com.dozor.serverinteraction.bean.Errors;

import javax.swing.*;
import java.awt.*;

/**
 * @author IGOR-K
 */
public class Dialogs {

    public static boolean showServErrDialogIfNeeded(Component parent, Errors errors) {
        if (errors != null) {
            showServErrDialog(parent, errors);
            return true;
        }
        return false;
    }

    public static void showServErrDialog(Component parent, Errors errors) {
        JOptionPane.showMessageDialog(parent,
                errors.name(),
                LocaleBundle.getInstance().getString("serv_error"),
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showServErrDialog(Component parent, String text) {
        JOptionPane.showMessageDialog(parent,
                text,
                LocaleBundle.getInstance().getString("serv_error"),
                JOptionPane.ERROR_MESSAGE);
    }

    public static void showErrDialog(Component parent, String text, String caption) {
        JOptionPane.showMessageDialog(parent,
                text,
                caption,
                JOptionPane.ERROR_MESSAGE);
    }
}
