package com.bhm.sdk.manager.library;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by bhm on 2018/5/2.
 */
public class StatusLayoutManager {

	private final OnViewClickListener listener;
	private final View contentView;
	private final ArrayList<View> views = new ArrayList<>();

	private StatusLayoutManager(Builder builder) {
		this.listener = builder.listener;
		View containerView = builder.rootView.findViewById(builder.containerViewId);
		ViewGroup viewGroup = (ViewGroup) containerView;
		contentView = viewGroup.findViewById(builder.contentViewId);
		ViewGroup.LayoutParams params = contentView.getLayoutParams();

		for (int layoutId : builder.viewsId){
			View itemView = LayoutInflater.from(builder.activity).inflate(layoutId, null);
			getAllChildViews(itemView);
			viewGroup.addView(itemView, params);
			itemView.setVisibility(View.GONE);
			views.add(itemView);
		}
	}

	public static Builder newBuilder(Activity activity) {
		return new Builder(activity);
	}

	private void getAllChildViews(View itemView){
		if (itemView instanceof ViewGroup) {
			ViewGroup vp = (ViewGroup) itemView;
			for (int i = 0; i < vp.getChildCount(); i++) {
				View viewChild = vp.getChildAt(i);
				getAllChildViews(viewChild);
			}
			vp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(v);
				}
			});
		}else{
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(v);
				}
			});
		}
	}

	/**
	 *  show content
	 */
	public void showContent() {
		if(contentView.getVisibility() == View.GONE){
			contentView.setVisibility(View.VISIBLE);
			for (View view : views){
				view.setVisibility(View.GONE);
			}
		}
	}

	public void hideAllLayout(){
		contentView.setVisibility(View.GONE);
		for (View view : views){
			view.setVisibility(View.GONE);
		}
	}

	/**
	 *  show position view
	 */
	public void showViewByPosition(int position) {
		contentView.setVisibility(View.GONE);
		for (int i = 0; i < views.size(); i++){
			if(position == i){
				views.get(i).setVisibility(View.VISIBLE);
			}else {
				views.get(i).setVisibility(View.GONE);
			}
		}
	}

	public static final class Builder {

		private Activity activity;
		private int contentViewId;
		private int containerViewId;
		private View rootView;
		private ArrayList<Integer> viewsId;
		private OnViewClickListener listener;

		Builder(Activity activity) {
			this.activity = activity;
		}


		public Builder contentViewId(int contentViewId) {
			this.contentViewId = contentViewId;
			return this;
		}

		public Builder containerViewId(int containerViewId) {
			this.containerViewId = containerViewId;
			return this;
		}

		public Builder rootView(View rootView) {
			this.rootView = rootView;
			return this;
		}

		public Builder itemViewsId(ArrayList<Integer> viewsId) {
			this.viewsId = viewsId;
			return this;
		}

		public Builder OnViewClickListener(OnViewClickListener listener) {
			this.listener = listener;
			return this;
		}

		public StatusLayoutManager build() {
			return new StatusLayoutManager(this);
		}
	}
}
