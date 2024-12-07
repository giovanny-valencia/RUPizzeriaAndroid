package rutgers.pizzeria.androidapp.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import rutgers.pizzeria.androidapp.R;
import rutgers.pizzeria.androidapp.models.pizza.Topping;


/**
 * ToppingAdapter is a RecyclerView.Adapter implementation used to display a list of toppings in a RecyclerView.
 * <p>
 * It binds {@link ToppingModel} data to RecyclerView rows, allowing users to select toppings for their pizza.
 * The adapter manages selection logic to ensure no more than {@value MAXIMUM_TOPPINGS} toppings are selected.
 * </p>
 *
 * <p>
 * This adapter is designed for use with the pizza creation screen in the application.
 * </p>
 *
 * @author Giovanny
 */
public class ToppingAdapter extends RecyclerView.Adapter<ToppingAdapter.ToppingHolder> {
    private static final int MAXIMUM_TOPPINGS = 7;

    Context context;
    ArrayList<ToppingModel> toppingModels;

    private final ArrayList<Topping> toppings = new ArrayList<>();


    /**
     * Constructs a new {@link ToppingAdapter}.
     *
     * @param context The application context.
     * @param toppingModels The list of available toppings to display in the RecyclerView.
     */
    public ToppingAdapter(Context context, ArrayList<ToppingModel> toppingModels){
        this.context = context;
        this.toppingModels = toppingModels;
    }

    /**
     * Inflates the layout for a RecyclerView row.
     *
     * @param parent The parent ViewGroup into which the new view will be added.
     * @param viewType The view type of the new view (unused in this implementation).
     * @return A {@link ToppingHolder} instance holding the inflated view.
     */
    @NonNull
    @Override
    public ToppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topping_item, parent, false);
        return new ToppingHolder(view);
    }

    /**
     * Binds the data for a specific position in the RecyclerView.
     * <p>
     * This method assigns data from the {@link ToppingModel} to the views in the corresponding row
     * and manages the selection and editing logic for each topping.
     * </p>
     *
     * @param holder The {@link ToppingHolder} instance for the row.
     * @param position The position of the item in the data list.
     */
    @Override
    public void onBindViewHolder(@NonNull ToppingHolder holder, int position) {
        //assigns values to views based on position of RV
        ToppingModel topping = toppingModels.get(position);

        holder.checkBox.setText(topping.getName());
        holder.imageView.setImageResource(topping.getImageResId());

        // Temporarily remove any existing listeners
        holder.checkBox.setOnCheckedChangeListener(null);

        // Set the CheckBox state based on the current item
        holder.checkBox.setChecked(topping.isChecked());

        // Set the editable state
        holder.checkBox.setEnabled(topping.isEditable());

        // Add a listener to handle clicks
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            topping.setChecked(isChecked); // Update the item's state


            Topping selectedTopping = topping.getToppingEnum(topping.getName().toUpperCase());

            if (!topping.isChecked()){
                toppings.remove(selectedTopping);
            }
            else toppings.add(selectedTopping);

            System.out.println(toppings);

            // Update selection logic
            updateSelectionLogic();
        });
    }

    /**
     * Returns the total number of items in the data list.
     *
     * @return The number of {@link ToppingModel} items.
     */
    @Override
    public int getItemCount() {
        return toppingModels.size();
    }

    /**
     * ViewHolder class for holding views related to each topping item.
     * <p>
     * This class provides references to the views (e.g., {@link CheckBox} and {@link ImageView})
     * used in each row of the RecyclerView.
     * </p>
     */
    public static class ToppingHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        ImageView imageView;

        /**
         * Constructs a new {@link ToppingHolder}.
         *
         * @param itemView The root view of the row layout.
         */
        public ToppingHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    /**
     * Updates the selection logic to enforce a maximum of 7 selected toppings.
     * <p>
     * When the maximum number of toppings is selected, all other CheckBoxes are disabled.
     * Otherwise, all CheckBoxes are re-enabled. This method refreshes the RecyclerView
     * to apply these changes.
     * </p>
     */
    private void updateSelectionLogic() {
        long selectedCount = toppingModels.stream().filter(ToppingModel::isChecked).count();

        if (selectedCount >= MAXIMUM_TOPPINGS) {
            // Disable other checkboxes except the selected ones
            for (ToppingModel model : toppingModels) {
                if (!model.isChecked()) {
                    model.setEditable(false);
                }
            }
        } else {
            // Re-enable all checkboxes if under the limit
            for (ToppingModel model : toppingModels) {
                model.setEditable(true);
            }
        }

        // Refresh the RecyclerView to apply the changes
        notifyDataSetChanged();
    }

    /**
     * Retrieves the list of currently selected toppings.
     *
     * @return An {@link ArrayList} of selected {@link Topping} objects.
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }
}
