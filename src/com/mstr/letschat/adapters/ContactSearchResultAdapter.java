package com.mstr.letschat.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mstr.letschat.R;
import com.mstr.letschat.model.ContactSearchResult;

public class ContactSearchResultAdapter extends BaseAdapter {
	
	public static interface OnAddButtonClickListener {
		public void onAddButtonClick(int position, View v);
	}
	
	private Context context;
	private List<ContactSearchResult> list;
	private OnAddButtonClickListener addButtonListener;
	
	public ContactSearchResultAdapter(Context context, List<ContactSearchResult> list) {
		this.context = context;
		this.list = list;
	}
	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public void setAddButtonListener(OnAddButtonClickListener addButtonListener) {
		this.addButtonListener = addButtonListener;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		
		if (convertView != null) {
			viewHolder = (ViewHolder)convertView.getTag();
		} else {
			convertView = LayoutInflater.from(context).inflate(R.layout.contact_search_result_item, parent, false);
			
			viewHolder = new ViewHolder();
			viewHolder.avatar = (ImageView)convertView.findViewById(R.id.avatar);
			viewHolder.userText = (TextView)convertView.findViewById(R.id.tv_user);
			viewHolder.nameText = (TextView)convertView.findViewById(R.id.tv_name);
			viewHolder.addButton = (Button)convertView.findViewById(R.id.btn_add);
			
			convertView.setTag(viewHolder);
		}
		
		ContactSearchResult item = (ContactSearchResult)getItem(position);
		viewHolder.userText.setText(item.getUser());
		viewHolder.nameText.setText(item.getName());
		viewHolder.addButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (addButtonListener != null) {
					addButtonListener.onAddButtonClick(position, v);
				}
			}
		});
		viewHolder.addButton.setText(item.isAdded() ? R.string.added : R.string.add);
		if (item.isAdded()) {
			viewHolder.addButton.setEnabled(false);
		}
		
		return convertView;
	}

	static class ViewHolder {
		ImageView avatar;
		TextView userText;
		TextView nameText;
		Button addButton;
	}
}