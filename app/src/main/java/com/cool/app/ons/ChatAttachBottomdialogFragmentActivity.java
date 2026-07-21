package com.error404.sudotalk;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.annotation.experimental.*;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.arch.core.*;
import androidx.camera.core.*;
import androidx.core.*;
import androidx.cursoradapter.*;
import androidx.customview.*;
import androidx.drawerlayout.*;
import androidx.exifinterface.*;
import androidx.fragment.*;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.interpolator.*;
import androidx.lifecycle.*;
import androidx.lifecycle.livedata.*;
import androidx.lifecycle.livedata.core.*;
import androidx.lifecycle.viewmodel.*;
import androidx.loader.*;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.savedstate.*;
import androidx.vectordrawable.*;
import androidx.vectordrawable.animated.*;
import androidx.versionedparcelable.*;
import androidx.viewpager.*;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.FirebaseApp;
import com.stdio.swipetoreply.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class ChatAttachBottomdialogFragmentActivity extends BottomSheetDialogFragment {
	
	private ArrayList<HashMap<String, Object>> gallary_list_map = new ArrayList<>();
	
	private LinearLayout back;
	private LinearLayout linear4;
	private RelativeLayout relativelayout1;
	private LinearLayout nestedScrollView1;
	private LinearLayout linear2;
	private LinearLayout main_layer1;
	private RecyclerView gallery_list;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.chat_attach_bottomdialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		back = _view.findViewById(R.id.back);
		linear4 = _view.findViewById(R.id.linear4);
		relativelayout1 = _view.findViewById(R.id.relativelayout1);
		nestedScrollView1 = _view.findViewById(R.id.nestedScrollView1);
		linear2 = _view.findViewById(R.id.linear2);
		main_layer1 = _view.findViewById(R.id.main_layer1);
		gallery_list = _view.findViewById(R.id.gallery_list);
	}
	
	private void initializeLogic() {
		for(int _repeat10 = 0; _repeat10 < (int)(10); _repeat10++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put("", "");
				gallary_list_map.add(_item);
			}
		}
		gallery_list.setAdapter(new Gallery_listAdapter(gallary_list_map));
		GridLayoutManager gridLM = new GridLayoutManager(getContext(), 2);
		gridLM.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				return position == 0 ? 2 : 1;
			}
		});
		gallery_list.setLayoutManager(gridLM);
	}
	
	public class Gallery_listAdapter extends RecyclerView.Adapter<Gallery_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gallery_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.layer1_rec_item, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout back = _view.findViewById(R.id.back);
			final LinearLayout camera_holder = _view.findViewById(R.id.camera_holder);
			final LinearLayout normal_item_holder = _view.findViewById(R.id.normal_item_holder);
			final RelativeLayout cam_relative = _view.findViewById(R.id.cam_relative);
			final LinearLayout camera_view = _view.findViewById(R.id.camera_view);
			final LinearLayout camera_overlay = _view.findViewById(R.id.camera_overlay);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final RelativeLayout norm_item_relative = _view.findViewById(R.id.norm_item_relative);
			final ImageView norm_img = _view.findViewById(R.id.norm_img);
			final LinearLayout norm_overlay = _view.findViewById(R.id.norm_overlay);
			
			if (_position == 0) {
				camera_holder.setVisibility(View.VISIBLE);
				normal_item_holder.setVisibility(View.VISIBLE);
				
				android.view.ViewGroup.LayoutParams params = norm_img.getLayoutParams();
				params.width = (int)(118 * getContext().getResources().getDisplayMetrics().density);
				params.height = (int)(118 * getContext().getResources().getDisplayMetrics().density);
				norm_img.setLayoutParams(params);
			} else {
				camera_holder.setVisibility(View.GONE);
				
				android.view.ViewGroup.LayoutParams params = norm_img.getLayoutParams();
				int cellSize = (int)(((getContext().getResources().getDisplayMetrics().widthPixels / 2) - (int)(6 * getContext().getResources().getDisplayMetrics().density)));
				params.width = cellSize;
				params.height = cellSize;
				norm_img.setLayoutParams(params);
				
				normal_item_holder.setVisibility(View.VISIBLE);
			}
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
		}
	}
}