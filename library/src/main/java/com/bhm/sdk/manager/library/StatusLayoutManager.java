package com.bhm.sdk.manager.library;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by bhm on 2018/5/2.
 */
public class StatusLayoutManager {

	private final OnViewClickListener listener;
	private final View contentView;
	private final LinkedHashMap<View, Object> views = new LinkedHashMap<>();

	private StatusLayoutManager(Builder builder) {
		this.listener = builder.listener;
		View containerView = builder.rootView.findViewById(builder.containerViewId);
		ViewGroup viewGroup = (ViewGroup) containerView;
		contentView = viewGroup.findViewById(builder.contentViewId);
		ViewGroup.LayoutParams params = contentView.getLayoutParams();

		for (Object o : builder.viewsId.entrySet()) {
			Map.Entry entry = (Map.Entry) o;
			int key = (int) entry.getKey();
			Object val = entry.getValue();
			View itemView = LayoutInflater.from(builder.activity).inflate(key, null);
			getAllChildViews(itemView);
			viewGroup.addView(itemView, params);
			itemView.setVisibility(View.GONE);
			views.put(itemView, val);
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
	public synchronized void showContent() {
		if(contentView.getVisibility() == View.GONE){
			contentView.setVisibility(View.VISIBLE);
			for (Object o : views.entrySet()) {
				Map.Entry entry = (Map.Entry) o;
				View view = (View) entry.getKey();
				if(view.getVisibility() == View.VISIBLE) {
					view.setVisibility(View.GONE);
				}
			}
		}
	}

	public synchronized void hideAllLayout(){
		if(contentView.getVisibility() == View.VISIBLE) {
			contentView.setVisibility(View.GONE);
		}
		for (Object o : views.entrySet()) {
			Map.Entry entry = (Map.Entry) o;
			View view = (View) entry.getKey();
			if(view.getVisibility() == View.VISIBLE) {
				view.setVisibility(View.GONE);
			}
		}
	}

	/**支持修改布局中的一个指定的TextView文字和指定的ImageView图片
	 * @param tag
	 * @param textViewId
	 * @param tips
	 * @param imageViewId
	 * @param resourceId
	 */
	public synchronized void showViewByTag(Object tag, int textViewId, String tips, int imageViewId, int resourceId){
		for (Object o : views.entrySet()) {
			Map.Entry entry = (Map.Entry) o;
			View view = (View) entry.getKey();
			Object o1 = entry.getValue();
			if(o1 == tag || tag.equals(o1)) {
				if(textViewId > 0 && !TextUtils.isEmpty(tips)){
					View textView = view.findViewById(textViewId);
					if(textView instanceof TextView){
						((TextView) textView).setText(tips);
					}
				}
				if(imageViewId > 0 && resourceId > 0){
					View imageView = view.findViewById(imageViewId);
					if(imageView instanceof ImageView){
						((ImageView) imageView).setImageResource(resourceId);
					}
				}
				if(view.getVisibility() == View.GONE) {
					view.setVisibility(View.VISIBLE);
				}
				if(contentView.getVisibility() == View.VISIBLE) {
					contentView.setVisibility(View.GONE);
				}
				break;
			}
		}
	}

	public synchronized void showViewByTag(Object tag){
		showViewByTag(tag, 0, null, 0, 0);
	}

	public synchronized void showViewByTag(Object tag, int textViewId, String tips){
		showViewByTag(tag, textViewId, tips, 0, 0);
	}

	public synchronized void showViewByTag(Object tag, int imageViewId, int resourceId){
		showViewByTag(tag, 0, null, imageViewId, resourceId);
	}

	public static final class Builder {

		private Activity activity;
		private int contentViewId;
		private int containerViewId;
		private View rootView;
		private LinkedHashMap<Integer, Object> viewsId;
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

		public Builder itemViewsId(LinkedHashMap<Integer, Object> viewsId) {
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
