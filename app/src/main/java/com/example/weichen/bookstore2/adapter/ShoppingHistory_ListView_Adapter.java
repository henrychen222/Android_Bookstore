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
public class ShoppingHistory_ListView_Adapter extends BaseAdapter {
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    Map<String, String> map = null;
    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    ViewHolder holder;
    /*构造函数*/
    public ShoppingHistory_ListView_Adapter(Context context, List<Map<String, String>> list) {
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
    }

    /*书中详细解释该方法*/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        //观察convertView随ListView滚动情况
        Log.v("ListAll_Activity", "getView " + position + " " + convertView);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.account_item2, null);
            holder = new ViewHolder();
                    /*得到各个控件的对象*/
            holder.isbn = (TextView) convertView.findViewById(R.id.ISBN);
            holder.title = (TextView) convertView.findViewById(R.id.TITLE);
            holder.quentity = (TextView)  convertView.findViewById(R.id.QUENTITY);
//                holder.bt1 = (Button) convertView.findViewById(R.id.ItemButton1);
//                holder.bt2 = (Button) convertView.findViewById(R.id.ItemButton2);
            convertView.setTag(holder);//绑定ViewHolder对象
        } else {
            holder = (ViewHolder) convertView.getTag();//取出ViewHolder对象
        }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
//        ArrayList<HashMap<String, Object>> items = getDate();
        holder.isbn.setText(listItem.get(position).get("bookId").toString());
        holder.title.setText(listItem.get(position).get("title").toString());
        holder.quentity.setText(listItem.get(position).get("quentity").toString());



            /*为Button添加点击事件*/
/*            holder.bt1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.v("ListAll_Activity", "你点击了按钮 + " + position);                                //打印Button的点击信息
                }
            });
            holder.bt2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.v("ListAll_Activity", "你点击了按钮 - " + position);                                //打印Button的点击信息
                }
            });*/

        return convertView;
    }

    /*添加一个得到数据的方法，方便使用*/
    private ArrayList<HashMap<String, Object>> getDate() {

       listItem = new ArrayList<HashMap<String, Object>>();
    /*为动态数组添加数据*/
        for (Map<String, String> item : list) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("bookId", item.get("bookId"));
            map.put("title", item.get("title"));
            map.put("quentity", item.get("quentity"));
            listItem.add(map);
        }

//        for (int i = 0; i < 30; i++) {
//            HashMap<String, Object> map = new HashMap<String, Object>();
//            map.put("ItemTitle", "第" + i + "行");
//            map.put("ItemText", "这是第" + i + "行");
//            listItem.add(map);
//        }
        return listItem;

    }

    /*存放控件*/
    public final class ViewHolder {
        public TextView isbn;
        public TextView title;
        public TextView quentity;
    }
}





