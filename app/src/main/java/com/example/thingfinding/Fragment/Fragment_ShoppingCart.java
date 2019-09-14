package com.example.thingfinding.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thingfinding.R;

public class Fragment_ShoppingCart extends Fragment implements View.OnClickListener{

    private ListView cartlist;
    private TextView emptyText;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_cart, container, false);
        cartlist = (ListView) view.findViewById(R.id.lvcart);
        emptyText = (TextView) view.findViewById(R.id.emptyText);
       // initView();
        emptyText.setText("空空如也，赶紧去添加你需要的东西吧！");

        return view;
    }

    private void initView() {
        cartlist = (ListView) getActivity().findViewById(R.id.lvcart);
        emptyText = (TextView) getActivity().findViewById(R.id.emptyText);
    }

    private void initEvent() {

    }

    public void onClick(View v){
        switch(v.getId()){

        }
    }
}
