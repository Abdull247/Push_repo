package com.error404.sudotalk;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.annotation.experimental.*;
import androidx.appcompat.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.*;
import androidx.arch.core.*;
import androidx.camera.core.*;
import androidx.core.*;
import androidx.core.widget.NestedScrollView;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.stdio.swipetoreply.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class ChatsFragFragmentActivity extends Fragment {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private ArrayList<HashMap<String, Object>> chat_list = new ArrayList<>();
	
	private LinearLayout top_bar;
	private NestedScrollView nestedScrollView1;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private TextView top_txt;
	private ImageView search_icon;
	private ImageView more_icon;
	private LinearLayout scrollHolder;
	private RecyclerView recyclerview1;
	
	private Intent intent = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	private DatabaseReference inbox = _firebase.getReference("inbox");
	private ChildEventListener _inbox_child_listener;
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.chats_frag_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		top_bar = _view.findViewById(R.id.top_bar);
		nestedScrollView1 = _view.findViewById(R.id.nestedScrollView1);
		linear3 = _view.findViewById(R.id.linear3);
		linear4 = _view.findViewById(R.id.linear4);
		top_txt = _view.findViewById(R.id.top_txt);
		search_icon = _view.findViewById(R.id.search_icon);
		more_icon = _view.findViewById(R.id.more_icon);
		scrollHolder = _view.findViewById(R.id.scrollHolder);
		recyclerview1 = _view.findViewById(R.id.recyclerview1);
		auth = FirebaseAuth.getInstance();
		
		_inbox_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		inbox.addChildEventListener(_inbox_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		top_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/sansita_black.ttf"), 0);
		search_icon.setColorFilter(getResources().getColor(R.color.text_header), PorterDuff.Mode.MULTIPLY);
		more_icon.setColorFilter(getResources().getColor(R.color.text_header), PorterDuff.Mode.MULTIPLY);
		String myUid = "";
		if (FirebaseAuth.getInstance().getCurrentUser() != null) {
			myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
		}
		
		if (!myUid.isEmpty()) {
			inbox.child(myUid).addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(DataSnapshot _dataSnapshot) {
					try {
						chat_list.clear();
						GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
						
						for (DataSnapshot _data : _dataSnapshot.getChildren()) {
							HashMap<String, Object> _map = _data.getValue(_ind);
							if (_map != null) {
								chat_list.add(_map);
							}
						}
						
						// Sort by timestamp, most recent first
						Collections.sort(chat_list, new Comparator<HashMap<String, Object>>() {
							@Override
							public int compare(HashMap<String, Object> a, HashMap<String, Object> b) {
								long timeA = a.containsKey("timestamp") ? Long.parseLong(String.valueOf(a.get("timestamp"))) : 0;
								long timeB = b.containsKey("timestamp") ? Long.parseLong(String.valueOf(b.get("timestamp"))) : 0;
								return Long.compare(timeB, timeA); // descending
							}
						});
						
						if (recyclerview1.getAdapter() == null) {
							recyclerview1.setLayoutManager(new LinearLayoutManager(getContext()));
							recyclerview1.setAdapter(new Recyclerview1Adapter(chat_list));
						} else {
							recyclerview1.getAdapter().notifyDataSetChanged();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void onCancelled(DatabaseError _databaseError) {}
			});
		}
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.chat_list_item, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final com.google.android.material.card.MaterialCardView it_cd = _view.findViewById(R.id.it_cd);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final TextView title_txt = _view.findViewById(R.id.title_txt);
			final TextView sub_txt = _view.findViewById(R.id.sub_txt);
			final TextView date_txt = _view.findViewById(R.id.date_txt);
			final com.google.android.material.card.MaterialCardView unread_cd = _view.findViewById(R.id.unread_cd);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView unread_txt = _view.findViewById(R.id.unread_txt);
			
			title_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 1);
			sub_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			date_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			unread_txt.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/ooo.ttf"), 0);
			try {
				HashMap<String, Object> _data = chat_list.get((int) _position);
				
				if (_data != null) {
					String msgSenderId = _data.containsKey("sender_id") ? String.valueOf(_data.get("sender_id")) : "";
					String msgReceiverId = _data.containsKey("receiver_id") ? String.valueOf(_data.get("receiver_id")) : "";
					String lastMsg = _data.containsKey("last_msg") ? String.valueOf(_data.get("last_msg")) : "";
					long timestamp = _data.containsKey("timestamp") ? Long.parseLong(String.valueOf(_data.get("timestamp"))) : 0;
					
					String myUid = FirebaseAuth.getInstance().getCurrentUser() != null 
					? FirebaseAuth.getInstance().getCurrentUser().getUid() 
					: "";
					
					final String otherUid = msgSenderId.equals(myUid) ? msgReceiverId : msgSenderId;
					
					if (!otherUid.isEmpty()) {
						users.child(otherUid).addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								try {
									if (_dataSnapshot.exists() && _dataSnapshot.getValue() != null) {
										GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
										HashMap<String, Object> _userMap = _dataSnapshot.getValue(_ind);
										
										if (_userMap != null && _userMap.containsKey("username")) {
											title_txt.setText(String.valueOf(_userMap.get("username")));
										}
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {}
						});
					}
					
					sub_txt.setText(lastMsg);
					
					// Format timestamp into 12-hour clock with AM/PM
					String formattedTime = "";
					try {
						java.text.SimpleDateFormat outputFmt = new java.text.SimpleDateFormat("hh:mm a");
						formattedTime = outputFmt.format(new java.util.Date(timestamp));
					} catch (Exception timeEx) {
						formattedTime = String.valueOf(timestamp);
					}
					date_txt.setText(formattedTime);
					
					// Click listener now lives in the SAME scope as otherUid, so it can see it
					it_cd.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							Intent intent = new Intent(getActivity(), ChatActivity.class);
							intent.putExtra("user_id", otherUid);
							getActivity().startActivity(intent);
							if (getActivity() != null) {
								getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
							}
						}
					});
				}
			} catch (Exception e) {
				e.printStackTrace();
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