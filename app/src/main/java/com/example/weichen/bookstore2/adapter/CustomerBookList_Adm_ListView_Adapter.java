package com.example.weichen.bookstore2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weichen.bookstore2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 新建一个类继承BaseAdapter，实现视图与数据的绑定
 */
public class CustomerBookList_Adm_ListView_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    Map<String, String> map = null;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    ViewHolder holder;
    Context context;
    /*构造函数*/
    public CustomerBookList_Adm_ListView_Adapter(Context context, List<Map<String, String>> list) {
        this.context = context;
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return getDate().size();//返回数组的长度
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
//        return position;
    }

    /*书中详细解释该方法*/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_adm_listcustomerbook, parent,false);
            holder = new ViewHolder();
                    /*得到各个控件的对象*/
            holder.ISBN = (TextView)  convertView.findViewById(R.id.ISBN);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.quentity = (TextView) convertView.findViewById(R.id.quentity);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
//        ArrayList<HashMap<String, Object>> items = getDate();
        holder.ISBN.setText(listItem.get(position).get("ISBN").toString());
        holder.title.setText(listItem.get(position).get("title").toString());
        holder.quentity.setText(listItem.get(position).get("quentity").toString());

        return convertView;
    }


    /*添加一个得到数据的方法，方便使用*/
    private ArrayList<HashMap<String, Object>> getDate() {

       listItem = new ArrayList<HashMap<String, Object>>();
    /*为动态数组添加数据*/
        for (Map<String, String> item : list) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ISBN", item.get("ISBN"));
            map.put("title", item.get("title"));
            map.put("quentity", item.get("quentity"));
            listItem.add(map);
        }
        return listItem;

    }

    /*存放控件*/
    public final class ViewHolder {
        public TextView ISBN;
        public TextView title;
        public TextView quentity;
    }
}





