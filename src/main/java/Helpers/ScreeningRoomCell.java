package Helpers;

import com.example.project.Model.ScreeningRoom;
import javafx.scene.control.ListCell;

/**
 * Custom ListCell implementation for displaying screening room details in a ListView.
 * This class customizes the appearance of each cell in the ListView to show only
 * the name of the screening room.
 */
public class ScreeningRoomCell extends ListCell<ScreeningRoom> {

    /**
     * Updates the content of a ListCell for the given ScreeningRoom object.
     *
     * @param pRoom The ScreeningRoom object to display in the cell. If null, the cell is cleared.
     * @param pEmpty A boolean indicating whether the cell is empty. If true, the cell is cleared.
     */
    @Override
    protected void updateItem(ScreeningRoom pRoom, boolean pEmpty) {
        super.updateItem(pRoom, pEmpty);

        if (pEmpty || pRoom == null) {
            // Clear the cell content if empty or no screening room is provided
            setText(null);
        } else {
            // Display the name of the screening room in the cell
            setText(pRoom.getName());
        }
    }
}
