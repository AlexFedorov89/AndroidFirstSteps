package com.fedorov.alex.whattheweatheristoday;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> {
    private int sizeItems = 0;
    private String[] cities;
    private OnItemClickListener itemClickListener;

    public CityAdapter(String[] cities) {
        this.cities = cities;
        this.sizeItems = cities.length;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.city_recycler_holder, parent, false);

        CityViewHolder cityViewHolder = new CityViewHolder(view);

        if (itemClickListener != null) {
            cityViewHolder.setOnClickListener(itemClickListener);
        }

        return cityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        holder.bind(cities[position]);
    }

    @Override
    public int getItemCount() {
        return sizeItems;
    }

    // Интерфейс для обработки нажатий как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public class CityViewHolder extends RecyclerView.ViewHolder {
        TextView cityView;

        public CityViewHolder(View itemView) {
            super(itemView);

            cityView = itemView.findViewById(R.id.cityTextView);
        }

        void bind(String city) {
            cityView.setText(city);
        }

        public void setOnClickListener(final OnItemClickListener listener) {
            cityView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Получаем позицию адаптера
                    int adapterPosition = getAdapterPosition();
                    // Проверяем ее на корректность
                    if (adapterPosition == RecyclerView.NO_POSITION) return;
                    listener.onItemClick(v, adapterPosition);
                }
            });
        }


    }
}
