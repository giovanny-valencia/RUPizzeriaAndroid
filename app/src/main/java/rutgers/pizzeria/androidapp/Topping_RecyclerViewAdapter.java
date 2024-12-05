package rutgers.pizzeria.androidapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Topping_RecyclerViewAdapter extends RecyclerView.Adapter<Topping_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<ToppingModel> toppingModels;
    public Topping_RecyclerViewAdapter(Context context, ArrayList<ToppingModel> toppingModels){
        this.context = context;
        this.toppingModels = toppingModels;
    }

    @NonNull
    @Override
    public Topping_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate layout (gives look to rows)
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.topping_item, parent, false);

        return new Topping_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Topping_RecyclerViewAdapter.MyViewHolder holder, int position) {
        //assigns values to views based on position of RV

        holder.checkBox.setText(toppingModels.get(position).getName());
        holder.imageView.setImageResource(toppingModels.get(position).getImageResId());
    }

    @Override
    public int getItemCount() {
        //how many items in total
        return toppingModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        //gets views from RV layout file

        CheckBox checkBox;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
