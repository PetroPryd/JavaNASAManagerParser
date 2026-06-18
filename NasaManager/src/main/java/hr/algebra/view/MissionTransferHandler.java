/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.model.Mission;
import java.awt.datatransfer.Transferable;
import java.util.function.Consumer;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.TransferHandler;

/**
 *
 * @author Petro Prydatkevych
 */
public class MissionTransferHandler extends TransferHandler {

    private final Consumer<Mission> onDrop;

    public MissionTransferHandler(Consumer<Mission> onDrop) {
        this.onDrop = onDrop;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Object value = ((JList<?>) c).getSelectedValue();
        return (value instanceof Mission m) ? new MissionTransferable(m) : null;
    }

    @Override
    public boolean canImport(TransferSupport support) {
        return support.isDataFlavorSupported(MissionTransferable.MISSION_FLAVOR);
    }

    @Override
    public boolean importData(TransferSupport support) {
        if (!canImport(support)) {
            return false;
        }
        try {
            Mission m = (Mission) support.getTransferable().getTransferData(MissionTransferable.MISSION_FLAVOR);
            onDrop.accept(m);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
