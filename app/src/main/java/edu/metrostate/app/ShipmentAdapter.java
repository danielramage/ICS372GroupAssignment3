package edu.metrostate.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShipmentAdapter extends BaseAdapter {

    private ArrayList<Shipment> shipments = new ArrayList<>();
    private Context context;

    //constructor takes Context obj and List of Book objects as param.
    public ShipmentAdapter(Context context, List<Shipment> shipments) {
        this.shipments.addAll(shipments);
        this.context = context;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //todo: create View obj

//        View view = LayoutInflater.from(context).inflate(R.layout.book_list_view, null);
        View view = LayoutInflater.from(context).inflate(R.layout.shipment_list, null);

        //Use View obj to find data from view id then assign to TextView object(s)

        //TextView title = view.findViewById(R.id.title_view);
       // TextView author = view.findViewById(R.id.author_view);
        TextView shipmentId= view.findViewById(R.id.shipmentId);

        //Book book = books.get(position);
        Shipment shipment = shipments.get(position);

//        title.setText(book.getTitle());
//        author.setText(book.getAuthor());

        shipmentId = view.findViewById(R.id.shipmentId);

        return view;
    }
}
