package course.labs.todomanager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ToDoListAdapter extends BaseAdapter {

	private final List<ToDoItem> mItems = new ArrayList<ToDoItem>();
	private final Context mContext;

	private static final String TAG = "Lab-UserInterface";

	private static class ViewHolderItem {
		TextView title;
		CheckBox status;
		TextView priority;
		TextView date;
		int position;
	}

	public ToDoListAdapter(Context context) {

		mContext = context;

	}

	// Add a ToDoItem to the adapter
	// Notify observers that the data set has changed

	public void add(ToDoItem item) {

		mItems.add(item);
		notifyDataSetChanged();

	}

	// Clears the list adapter of all items.

	public void clear() {

		mItems.clear();
		notifyDataSetChanged();

	}

	// Returns the number of ToDoItems

	@Override
	public int getCount() {

		return mItems.size();

	}

	// Retrieve the number of ToDoItems

	@Override
	public Object getItem(int pos) {

		return mItems.get(pos);

	}

	// Get the ID for the ToDoItem
	// In this case it's just the position

	@Override
	public long getItemId(int pos) {

		return pos;

	}

	// Create a View for the ToDoItem at specified position
	// Remember to check whether convertView holds an already allocated View
	// before created a new View.
	// Consider using the ViewHolder pattern to make scrolling more efficient
	// See: http://developer.android.com/training/improving-layouts/smooth-scrolling.html
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderItem viewHolder;

		final ToDoItem toDoItem = (ToDoItem) getItem(position);

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.todo_item, parent, false);
			viewHolder = new ViewHolderItem();
			viewHolder.title = (TextView) convertView.findViewById(R.id.titleView);
			viewHolder.status = (CheckBox) convertView.findViewById((R.id.statusCheckBox));
			viewHolder.priority = (TextView) convertView.findViewById(R.id.priorityView);
			viewHolder.date = (TextView) convertView.findViewById(R.id.dateView);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}

		viewHolder.title.setText(toDoItem.getTitle());
		viewHolder.status.setChecked(toDoItem.getStatus() == ToDoItem.Status.DONE);

		viewHolder.status.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
										 boolean isChecked) {
				if (isChecked) {
					toDoItem.setStatus(ToDoItem.Status.DONE);
				} else {
					toDoItem.setStatus(ToDoItem.Status.NOTDONE);
				}
			}
		});

		viewHolder.priority.setText(toDoItem.getPriority().toString());
		viewHolder.date.setText(ToDoItem.FORMAT.format(toDoItem.getDate()));

		return convertView;

	}
}
