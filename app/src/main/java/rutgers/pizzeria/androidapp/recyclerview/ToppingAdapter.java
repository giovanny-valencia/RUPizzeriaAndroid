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

public class ToppingAdapter extends RecyclerView.Adapter<ToppingAdapter.ToppingHolder> {
    Context context;
    ArrayList<ToppingModel> toppingModels;
    public ToppingAdapter(Context context, ArrayList<ToppingModel> toppingModels){
        this.context = context;
        this.toppingModels = toppingModels;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent of view
     * @param viewType of view
     * @return the view
     */
    @NonNull
    @Override
    public ToppingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout (gives look to rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topping_item, parent, false);
        return new ToppingHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
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

        // Add a new listener to update the item's state
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked)
                -> topping.setChecked(isChecked)); // Update the item's state in the model
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        //how many items in total
        return toppingModels.size();
    }

    public static class ToppingHolder extends RecyclerView.ViewHolder{
        //gets views from RV layout file

        CheckBox checkBox;
        ImageView imageView;

        public ToppingHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
