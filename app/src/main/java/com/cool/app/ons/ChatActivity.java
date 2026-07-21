package com.error404.sudotalk;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
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
import com.google.android.material.card.*;
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
import de.hdodenhof.circleimageview.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import androidx.core.content.ContextCompat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;


public class ChatActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private String receiver_id = "";
	private String sender_id = "";
	private String chatroom = "";
	private String chatcopy = "";
	private HashMap<String, Object> msg_map = new HashMap<>();
	private HashMap<String, Object> inbox_sender_map = new HashMap<>();
	private HashMap<String, Object> inbox_receiver_map = new HashMap<>();
	private String msg_key = "";
	private String message_text = "";
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> status = new HashMap<>();
	private String msg_trim = "";
	private HashMap<String, Object> stauts_typing_map = new HashMap<>();
	private double reply_position = 0;
	private boolean is_reply = false;
	
	private ArrayList<HashMap<String, Object>> chat_list_map = new ArrayList<>();
	
	private RelativeLayout relativelayout1;
	private LinearLayout content_background;
	private LinearLayout bg;
	private LinearLayout top_bar;
	private RecyclerView chat_list;
	private LinearLayout back_holder;
	private CircleImageView circleimageview1;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private MaterialCardView back_cd_bg;
	private LinearLayout linear3;
	private ImageView imageview1;
	private TextView username_txt;
	private TextView textview2;
	private MaterialCardView call_cd_bg;
	private MaterialCardView menu_cd_bg;
	private LinearLayout linear5;
	private ImageView imageview2;
	private LinearLayout linear4;
	private ImageView imageview3;
	private MaterialCardView input_holder;
	private LinearLayout input_main_in_cd_holder;
	private LinearLayout reply_mode_holder;
	private LinearLayout input_mode_holder;
	private LinearLayout reply_inner1;
	private LinearLayout reply_inner2;
	private LinearLayout reply_inner3;
	private ImageView imageview4;
	private TextView reply_mode_header;
	private TextView reply_mode_sub;
	private MaterialCardView reply_cancel_cd;
	private LinearLayout linear10;
	private ImageView imageview6;
	private LinearLayout linear7;
	private LinearLayout input_edit_holder;
	private LinearLayout linear8;
	private ImageView state_ic;
	private EditText msg_input;
	private ImageView attach_ic;
	private MaterialCardView send_cd;
	private LinearLayout linear9;
	private ImageView send_img;
	
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
	private DatabaseReference users = _firebase.getReference("users");
	private ChildEventListener _users_child_listener;
	private DatabaseReference inbox = _firebase.getReference("inbox");
	private ChildEventListener _inbox_child_listener;
	private DatabaseReference Chat1 = _firebase.getReference("+chatroom+");
	private ChildEventListener _Chat1_child_listener;
	private DatabaseReference Chat2 = _firebase.getReference("+chatcopy+");
	private ChildEventListener _Chat2_child_listener;
	private Calendar cal = Calendar.getInstance();
	private DatabaseReference user_status = _firebase.getReference("user_status");
	private ChildEventListener _user_status_child_listener;
	private TimerTask timer;
	private SharedPreferences reply;
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.chat);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		relativelayout1 = findViewById(R.id.relativelayout1);
		content_background = findViewById(R.id.content_background);
		bg = findViewById(R.id.bg);
		top_bar = findViewById(R.id.top_bar);
		chat_list = findViewById(R.id.chat_list);
		back_holder = findViewById(R.id.back_holder);
		circleimageview1 = findViewById(R.id.circleimageview1);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		back_cd_bg = findViewById(R.id.back_cd_bg);
		linear3 = findViewById(R.id.linear3);
		imageview1 = findViewById(R.id.imageview1);
		username_txt = findViewById(R.id.username_txt);
		textview2 = findViewById(R.id.textview2);
		call_cd_bg = findViewById(R.id.call_cd_bg);
		menu_cd_bg = findViewById(R.id.menu_cd_bg);
		linear5 = findViewById(R.id.linear5);
		imageview2 = findViewById(R.id.imageview2);
		linear4 = findViewById(R.id.linear4);
		imageview3 = findViewById(R.id.imageview3);
		input_holder = findViewById(R.id.input_holder);
		input_main_in_cd_holder = findViewById(R.id.input_main_in_cd_holder);
		reply_mode_holder = findViewById(R.id.reply_mode_holder);
		input_mode_holder = findViewById(R.id.input_mode_holder);
		reply_inner1 = findViewById(R.id.reply_inner1);
		reply_inner2 = findViewById(R.id.reply_inner2);
		reply_inner3 = findViewById(R.id.reply_inner3);
		imageview4 = findViewById(R.id.imageview4);
		reply_mode_header = findViewById(R.id.reply_mode_header);
		reply_mode_sub = findViewById(R.id.reply_mode_sub);
		reply_cancel_cd = findViewById(R.id.reply_cancel_cd);
		linear10 = findViewById(R.id.linear10);
		imageview6 = findViewById(R.id.imageview6);
		linear7 = findViewById(R.id.linear7);
		input_edit_holder = findViewById(R.id.input_edit_holder);
		linear8 = findViewById(R.id.linear8);
		state_ic = findViewById(R.id.state_ic);
		msg_input = findViewById(R.id.msg_input);
		attach_ic = findViewById(R.id.attach_ic);
		send_cd = findViewById(R.id.send_cd);
		linear9 = findViewById(R.id.linear9);
		send_img = findViewById(R.id.send_img);
		auth = FirebaseAuth.getInstance();
		reply = getSharedPreferences("reply_data", Activity.MODE_PRIVATE);
		
		back_cd_bg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		call_cd_bg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		menu_cd_bg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		reply_cancel_cd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				reply_mode_holder.animate()
				.alpha(0f)
				.translationY(30f)
				.setDuration(180)
				.withEndAction(new Runnable() {
					@Override
					public void run() {
						reply_mode_holder.setVisibility(View.GONE);
						reply_mode_holder.setTranslationY(0f);
					}
				})
				.start();
				
				is_reply = false;
				reply_position = -1;
				
				reply.edit().remove("reply_header").commit();
				reply.edit().remove("reply_text").commit();
				reply.edit().remove("reply_position").commit();
			}
		});
		
		msg_input.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				msg_trim = _charSeq.toString().trim();
				
				if (timer != null) {
					timer.cancel();
				}
				
				if (_charSeq.length() > 0) {
					stauts_typing_map = new HashMap<>();
					stauts_typing_map.put("status", "typing....");
					user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(stauts_typing_map);
					stauts_typing_map.clear();
					
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									status = new HashMap<>();
									status.put("status", "Online");
									user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
									status.clear();
								}
							});
						}
					};
					_timer.schedule(timer, 2600);
				} else {
					status = new HashMap<>();
					status.put("status", "Online");
					user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
					status.clear();
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		attach_ic.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ChatAttachBottomdialogFragmentActivity bottomSheet = new ChatAttachBottomdialogFragmentActivity();
				bottomSheet.setStyle(androidx.fragment.app.DialogFragment.STYLE_NORMAL, R.style.AttachBottomSheetTheme);
				bottomSheet.show(getSupportFragmentManager(), "ChatAttachBottomSheet");
			}
		});
		
		send_cd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				try {
					message_text = msg_input.getText().toString().trim();
					
					if (!message_text.isEmpty() && !sender_id.isEmpty() && !receiver_id.isEmpty()) {
						cal = java.util.Calendar.getInstance();
						long timestamp = cal.getTimeInMillis();
						String dateStr = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(cal.getTime());
						
						msg_key = Chat1.push().getKey();
						
						if (msg_key != null) {
							msg_map = new java.util.HashMap<>();
							msg_map.put("sender_id", sender_id);
							msg_map.put("receiver_id", receiver_id);
							msg_map.put("message", message_text);
							msg_map.put("msg_key", msg_key);
							msg_map.put("timestamp", timestamp);
							msg_map.put("date", dateStr);
							msg_map.put("sent", true);
							
							if (is_reply) {
								msg_map.put("is_reply", true);
								msg_map.put("parent_msg", reply.getString("reply_text", ""));
								msg_map.put("parent_msg_key", reply.getString("reply_msg_key", ""));
							} else {
								msg_map.put("is_reply", false);
							}
							
							Chat1.child(msg_key).updateChildren(msg_map);
							Chat2.child(msg_key).updateChildren(msg_map);
							
							inbox_sender_map = new java.util.HashMap<>();
							inbox_sender_map.put("sender_id", sender_id);
							inbox_sender_map.put("receiver_id", receiver_id);
							inbox_sender_map.put("last_msg", message_text);
							inbox_sender_map.put("timestamp", timestamp);
							
							inbox_receiver_map = new java.util.HashMap<>();
							inbox_receiver_map.put("sender_id", sender_id);
							inbox_receiver_map.put("receiver_id", receiver_id);
							inbox_receiver_map.put("last_msg", message_text);
							inbox_receiver_map.put("timestamp", timestamp);
							
							FirebaseDatabase.getInstance().getReference("inbox").child(sender_id).child(receiver_id).updateChildren(inbox_sender_map);
							FirebaseDatabase.getInstance().getReference("inbox").child(receiver_id).child(sender_id).updateChildren(inbox_receiver_map);
							
							// Cancel any pending typing timer and reset own status to Online
							if (timer != null) {
								timer.cancel();
							}
							status = new HashMap<>();
							status.put("status", "Online");
							user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
							status.clear();
							
							msg_input.setText("");
							
							// Reset reply state after send
							reply_mode_holder.setVisibility(View.GONE);
							reply_mode_holder.setTranslationY(0f);
							is_reply = false;
							reply_position = -1;
							reply.edit().remove("reply_header").commit();
							reply.edit().remove("reply_text").commit();
							reply.edit().remove("reply_position").commit();
							reply.edit().remove("reply_msg_key").commit();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
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
		
		_Chat1_child_listener = new ChildEventListener() {
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
		Chat1.addChildEventListener(_Chat1_child_listener);
		
		_Chat2_child_listener = new ChildEventListener() {
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
		Chat2.addChildEventListener(_Chat2_child_listener);
		
		_user_status_child_listener = new ChildEventListener() {
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
		user_status.addChildEventListener(_user_status_child_listener);
		
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
		// 1. Fetch the system night mode configuration flags safely
		int nightModeFlags = ChatActivity.this.getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
		
		// 2. Declare the drawables ONCE at the top to prevent duplicate variable errors
		android.graphics.drawable.Drawable doodleBackground;
		android.graphics.drawable.Drawable shadowOverlay = ChatActivity.this.getResources().getDrawable(R.drawable.simar_shadow_black);
		
		// 3. Conditionally assign the correct background image asset
		if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
			doodleBackground = ChatActivity.this.getResources().getDrawable(R.drawable.bg_black);
		} else {
			doodleBackground = ChatActivity.this.getResources().getDrawable(R.drawable.bg_light_neon);
		}
		
		// 4. Combine the assigned layers and pass it to the Window background
		android.graphics.drawable.Drawable[] layers = new android.graphics.drawable.Drawable[]{ doodleBackground, shadowOverlay };
		android.graphics.drawable.LayerDrawable layeredBackground = new android.graphics.drawable.LayerDrawable(layers);
		getWindow().setBackgroundDrawable(layeredBackground);
		
		// 5. Let the app draw behind the system bars/keyboard so the Window background never resizes
		androidx.core.view.WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		
		// 6. Manually apply status bar padding to top_bar, and translate "bg" with the keyboard
		final View rootLayoutForInsets = findViewById(R.id.relativelayout1);
		final View topBarForInsets = findViewById(R.id.top_bar);
		final View chatInputContainerForInsets = findViewById(R.id.bg);
		
		final int topBarBasePaddingLeft = topBarForInsets.getPaddingLeft();
		final int topBarBasePaddingRight = topBarForInsets.getPaddingRight();
		final int topBarBasePaddingTop = topBarForInsets.getPaddingTop();
		final int topBarBasePaddingBottom = topBarForInsets.getPaddingBottom();
		
		androidx.core.view.ViewCompat.setWindowInsetsAnimationCallback(rootLayoutForInsets, new androidx.core.view.WindowInsetsAnimationCompat.Callback(androidx.core.view.WindowInsetsAnimationCompat.Callback.DISPATCH_MODE_STOP) {
			@Override
			public androidx.core.view.WindowInsetsCompat onProgress(androidx.core.view.WindowInsetsCompat insets, java.util.List<androidx.core.view.WindowInsetsAnimationCompat> runningAnimations) {
				int imeHeight = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.ime()).bottom;
				chatInputContainerForInsets.setTranslationY(-imeHeight);
				return insets;
			}
		});
		
		androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener(rootLayoutForInsets, new androidx.core.view.OnApplyWindowInsetsListener() {
			@Override
			public androidx.core.view.WindowInsetsCompat onApplyWindowInsets(View v, androidx.core.view.WindowInsetsCompat insets) {
				int imeHeight = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.ime()).bottom;
				int statusBarHeight = insets.getInsets(androidx.core.view.WindowInsetsCompat.Type.statusBars()).top;
				
				chatInputContainerForInsets.setTranslationY(-imeHeight);
				
				topBarForInsets.setPadding(
				topBarBasePaddingLeft,
				topBarBasePaddingTop + statusBarHeight,
				topBarBasePaddingRight,
				topBarBasePaddingBottom
				);
				
				return insets;
			}
		});
		
		androidx.core.view.ViewCompat.requestApplyInsets(rootLayoutForInsets);
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = ChatActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(getResources().getColor(R.color.background));
		}
		_changeActivityFont("ooo");
		try {
			if (getIntent().hasExtra("user_id")) {
				receiver_id = getIntent().getStringExtra("user_id");
			} else {
				receiver_id = "";
			}
			
			if (FirebaseAuth.getInstance().getCurrentUser() != null) {
				sender_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
			} else {
				sender_id = "";
			}
			
			if (!sender_id.isEmpty() && !receiver_id.isEmpty()) {
				chatroom = "chat/".concat(sender_id).concat("/").concat(receiver_id);
				chatcopy = "chat/".concat(receiver_id).concat("/").concat(sender_id);
				
				Chat1 = FirebaseDatabase.getInstance().getReference(chatroom);
				Chat2 = FirebaseDatabase.getInstance().getReference(chatcopy);
				
				Chat1.addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						try {
							chat_list_map.clear();
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								if (_map != null) {
									chat_list_map.add(_map);
								}
							}
							
							if (chat_list.getAdapter() == null) {
								LinearLayoutManager lm = new LinearLayoutManager(ChatActivity.this);
								lm.setStackFromEnd(true);
								chat_list.setLayoutManager(lm);
								chat_list.setAdapter(new Chat_listAdapter(chat_list_map));
							} else {
								chat_list.getAdapter().notifyDataSetChanged();
								chat_list.post(new Runnable() {
									@Override
									public void run() {
										if (chat_list_map.size() > 0) {
											((LinearLayoutManager) chat_list.getLayoutManager())
											.scrollToPositionWithOffset(chat_list_map.size() - 1, 0);
										}
									}
								});
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {}
				});
				
				// Listen to receiver's status → update textview2
				user_status.child(receiver_id).addValueEventListener(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot snapshot) {
						try {
							if (snapshot.exists() && snapshot.getValue() != null) {
								GenericTypeIndicator<HashMap<String, Object>> ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								HashMap<String, Object> statusMap = snapshot.getValue(ind);
								
								if (statusMap != null) {
									String currentStatus = String.valueOf(statusMap.get("status"));
									
									if ("typing....".equals(currentStatus)) {
										textview2.setText("typing....");
									} else if ("Online".equalsIgnoreCase(currentStatus)) {
										textview2.setText("online");
									} else {
										if (statusMap.containsKey("last_seen")) {
											String raw = String.valueOf(statusMap.get("last_seen"));
											try {
												java.text.SimpleDateFormat inputFmt = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
												java.text.SimpleDateFormat outputFmt = new java.text.SimpleDateFormat("hh:mm a");
												java.util.Date parsed = inputFmt.parse(raw);
												String formatted = outputFmt.format(parsed);
												textview2.setText("last seen online " + formatted);
											} catch (Exception parseEx) {
												textview2.setText("last seen online " + raw);
											}
										} else {
											textview2.setText("offline");
										}
									}
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					@Override
					public void onCancelled(DatabaseError error) {}
				});
				
				users.child(receiver_id).addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						try {
							if (_dataSnapshot.exists() && _dataSnapshot.getValue() != null) {
								GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
								HashMap<String, Object> _userMap = _dataSnapshot.getValue(_ind);
								
								if (_userMap != null) {
									if (_userMap.containsKey("username")) {
										username_txt.setText(String.valueOf(_userMap.get("username")));
									}
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final View rootView = findViewById(android.R.id.content);
		final int[] lastBottomPadding = {(int) (60 * getResources().getDisplayMetrics().density)};
		final boolean[] isFirstLayout = {true};
		
		rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				android.graphics.Rect visibleFrame = new android.graphics.Rect();
				rootView.getWindowVisibleDisplayFrame(visibleFrame);
				
				int screenHeight = rootView.getRootView().getHeight();
				int keypadHeight = screenHeight - visibleFrame.bottom;
				
				int basePadding = (int) (60 * getResources().getDisplayMetrics().density);
				int targetPadding;
				
				if (keypadHeight > screenHeight * 0.15) {
					targetPadding = keypadHeight + basePadding;
				} else {
					targetPadding = basePadding;
				}
				
				if (isFirstLayout[0]) {
					isFirstLayout[0] = false;
					lastBottomPadding[0] = targetPadding;
					chat_list.setPadding(
					chat_list.getPaddingLeft(),
					chat_list.getPaddingTop(),
					chat_list.getPaddingRight(),
					targetPadding
					);
					return;
				}
				
				if (targetPadding != lastBottomPadding[0]) {
					final int finalTargetPadding = targetPadding;
					
					ValueAnimator paddingAnimator = ValueAnimator.ofInt(lastBottomPadding[0], finalTargetPadding);
					paddingAnimator.setDuration(220);
					paddingAnimator.setInterpolator(new DecelerateInterpolator());
					paddingAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(ValueAnimator animation) {
							int animatedValue = (int) animation.getAnimatedValue();
							chat_list.setPadding(
							chat_list.getPaddingLeft(),
							chat_list.getPaddingTop(),
							chat_list.getPaddingRight(),
							animatedValue
							);
							if (chat_list_map.size() > 0) {
								chat_list.scrollToPosition(chat_list_map.size() - 1);
							}
						}
					});
					paddingAnimator.start();
					
					lastBottomPadding[0] = finalTargetPadding;
				}
			}
		});
		status = new HashMap<>();
		status.put("status", "online");
		user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
		status.clear();
		msg_input.setFocusable(true);
		msg_input.setFocusableInTouchMode(true);
		msg_input.setClickable(true);
		msg_input.setLongClickable(true);
		msg_input.setCursorVisible(true);
		
		msg_input.setHighlightColor(getResources().getColor(R.color.primary_variant));
		
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
				android.graphics.drawable.Drawable cursorDrawable = getResources().getDrawable(R.drawable.cursor_color);
				msg_input.setTextCursorDrawable(cursorDrawable);
			} else {
				java.lang.reflect.Field cursorField = TextView.class.getDeclaredField("mCursorDrawableRes");
				cursorField.setAccessible(true);
				cursorField.set(msg_input, R.drawable.cursor_color);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			java.lang.reflect.Field editorField = TextView.class.getDeclaredField("mEditor");
			editorField.setAccessible(true);
			Object editor = editorField.get(msg_input);
			String[] handleNames = {"mSelectHandleLeft", "mSelectHandleRight", "mSelectHandleCenter"};
			for (String handleName : handleNames) {
				java.lang.reflect.Field handleField = editor.getClass().getDeclaredField(handleName);
				handleField.setAccessible(true);
				android.graphics.drawable.Drawable handleDrawable = getResources().getDrawable(R.drawable.cursor_color);
				handleDrawable.setColorFilter(getResources().getColor(R.color.primary), android.graphics.PorterDuff.Mode.SRC_IN);
				handleField.set(editor, handleDrawable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		final boolean[] isSendState = {false};
		
		msg_input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {}
			
			@Override
			public void afterTextChanged(Editable s) {
				boolean hasText = s.toString().trim().length() > 0;
				
				if (hasText && !isSendState[0]) {
					isSendState[0] = true;
					
					send_img.animate().alpha(0f).setDuration(120).withEndAction(new Runnable() {
						@Override
						public void run() {
							send_img.setImageResource(R.drawable.send_plane_24);
							send_img.animate().alpha(1f).setDuration(150).start();
						}
					}).start();
					
					attach_ic.animate()
					.alpha(0f)
					.scaleX(0.4f)
					.scaleY(0.4f)
					.translationX(attach_ic.getWidth() / 2f)
					.setDuration(200)
					.withEndAction(new Runnable() {
						@Override
						public void run() {
							attach_ic.setVisibility(View.GONE);
						}
					})
					.start();
					
				} else if (!hasText && isSendState[0]) {
					isSendState[0] = false;
					
					send_img.animate().alpha(0f).setDuration(120).withEndAction(new Runnable() {
						@Override
						public void run() {
							send_img.setImageResource(R.drawable.input_mic_pressed);
							send_img.animate().alpha(1f).setDuration(150).start();
						}
					}).start();
					
					attach_ic.setVisibility(View.VISIBLE);
					attach_ic.setAlpha(0f);
					attach_ic.setScaleX(0.4f);
					attach_ic.setScaleY(0.4f);
					attach_ic.setTranslationX(attach_ic.getWidth() / 2f);
					
					attach_ic.animate()
					.alpha(1f)
					.scaleX(1f)
					.scaleY(1f)
					.translationX(0f)
					.setDuration(220)
					.start();
				}
			}
		});
		textview2.setSingleLine(true);
		textview2.setMaxLines(1);
		textview2.setEllipsize(android.text.TextUtils.TruncateAt.END);
		textview2.setHorizontallyScrolling(false);
		reply_mode_holder.setVisibility(View.GONE);
		
		SwipeController controller = new SwipeController(ChatActivity.this, new ISwipeControllerActions() {
			@Override
			public void onSwipePerformed(int position) {
				try {
					HashMap<String, Object> swipedMessage = chat_list_map.get((int) position);
					
					if (swipedMessage != null && swipedMessage.containsKey("message")) {
						String replyText = String.valueOf(swipedMessage.get("message"));
						String replyHeader = "Reply to " + username_txt.getText().toString();
						
						reply_mode_sub.setSingleLine(true);
						reply_mode_sub.setMaxLines(1);
						reply_mode_sub.setEllipsize(android.text.TextUtils.TruncateAt.END);
						reply_mode_sub.setHorizontallyScrolling(false);
						
						reply_mode_header.setText(replyHeader);
						reply_mode_sub.setText(replyText);
						
						reply_position = position;
						is_reply = true;
						
						reply.edit().putString("reply_header", replyHeader).commit();
						reply.edit().putString("reply_text", replyText).commit();
						reply.edit().putString("reply_position", String.valueOf(position)).commit();
						
						reply_mode_holder.setVisibility(View.VISIBLE);
						reply_mode_holder.setAlpha(0f);
						reply_mode_holder.setTranslationY(30f);
						reply_mode_holder.animate()
						.alpha(1f)
						.translationY(0f)
						.setDuration(220)
						.start();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(controller);
		itemTouchHelper.attachToRecyclerView(chat_list);
		((EditText)msg_input).setMaxLines((int)2);
	}
	
	@Override
	public void onBackPressed() {
		if (timer != null) {
			timer.cancel();
		}
		status = new HashMap<>();
		status.put("status", "Online");
		user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
		status.clear();
		
		finish();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		// 1. Fetch the system night mode configuration flags safely
		int nightModeFlags = ChatActivity.this.getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
		
		// 2. Declare the drawables ONCE at the top to prevent duplicate variable errors
		android.graphics.drawable.Drawable doodleBackground;
		android.graphics.drawable.Drawable shadowOverlay = ChatActivity.this.getResources().getDrawable(R.drawable.simar_shadow_black);
		
		// 3. Conditionally assign the correct background image asset
		if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
			// It's dark mode
			doodleBackground = ChatActivity.this.getResources().getDrawable(R.drawable.bg_black);
		} else {
			// It's light mode
			doodleBackground = ChatActivity.this.getResources().getDrawable(R.drawable.bg_light_neon);
		}
		
		// 4. Combine the assigned layers and pass it to the Window background
		android.graphics.drawable.Drawable[] layers = new android.graphics.drawable.Drawable[]{ doodleBackground, shadowOverlay };
		android.graphics.drawable.LayerDrawable layeredBackground = new android.graphics.drawable.LayerDrawable(layers);
		getWindow().setBackgroundDrawable(layeredBackground);
		
		status = new HashMap<>();
		status.put("status", "online");
		user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
		status.clear();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		// 1. Fetch the system night mode configuration flags safely
		int nightModeFlags = ChatActivity.this.getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
		
		// 2. Declare the drawables ONCE at the top to prevent duplicate variable errors
		android.graphics.drawable.Drawable doodleBackground;
		android.graphics.drawable.Drawable shadowOverlay = ChatActivity.this.getResources().getDrawable(R.drawable.simar_shadow_black);
		
		// 3. Conditionally assign the correct background image asset
		if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
			// It's dark mode
			doodleBackground = ChatActivity.this.getResources().getDrawable(R.drawable.bg_black);
		} else {
			// It's light mode
			doodleBackground = ChatActivity.this.getResources().getDrawable(R.drawable.bg_light_neon);
		}
		
		// 4. Combine the assigned layers and pass it to the Window background
		android.graphics.drawable.Drawable[] layers = new android.graphics.drawable.Drawable[]{ doodleBackground, shadowOverlay };
		android.graphics.drawable.LayerDrawable layeredBackground = new android.graphics.drawable.LayerDrawable(layers);
		getWindow().setBackgroundDrawable(layeredBackground);
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		status = new HashMap<>();
		status.put("status", "offline");
		
		// Add last seen timestamp
		long timestamp = Calendar.getInstance().getTimeInMillis();
		String dateStr = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(Calendar.getInstance().getTime());
		status.put("last_seen", dateStr);
		status.put("last_seen_timestamp", timestamp);
		
		user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
		status.clear();
	}
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".ttf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			} else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				} else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					} else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _Round(final double _one, final double _two, final double _three, final double _four, final String _color, final View _view) {
		Double left_top = _one;
		Double right_top = _two;
		Double left_bottom = _three;
		Double right_bottom = _four;
		
		int resolvedColor;
		String trimmedColor = _color.trim();
		
		if (trimmedColor.startsWith("@color/")) {
			String colorName = trimmedColor.substring(7);
			int colorResId = getResources().getIdentifier(colorName, "color", getPackageName());
			
			if (colorResId != 0) {
				resolvedColor = androidx.core.content.ContextCompat.getColor(this, colorResId);
			} else {
				resolvedColor = Color.parseColor("#000000");
			}
		} else {
			resolvedColor = Color.parseColor(trimmedColor);
		}
		
		if (_view instanceof com.google.android.material.card.MaterialCardView) {
			// MaterialCardView supports per-corner radii via ShapeAppearanceModel
			com.google.android.material.card.MaterialCardView materialCard = (com.google.android.material.card.MaterialCardView) _view;
			
			materialCard.setCardBackgroundColor(resolvedColor);
			
			com.google.android.material.shape.ShapeAppearanceModel shapeModel =
			com.google.android.material.shape.ShapeAppearanceModel.builder()
			.setTopLeftCornerSize(left_top.floatValue())
			.setTopRightCornerSize(right_top.floatValue())
			.setBottomLeftCornerSize(left_bottom.floatValue())
			.setBottomRightCornerSize(right_bottom.floatValue())
			.build();
			
			materialCard.setShapeAppearanceModel(shapeModel);
			
		} else if (_view instanceof androidx.cardview.widget.CardView) {
			// Base CardView only supports ONE uniform radius, no per-corner control
			androidx.cardview.widget.CardView card = (androidx.cardview.widget.CardView) _view;
			
			card.setCardBackgroundColor(resolvedColor);
			
			// Average the four values since CardView can't do per-corner radii
			float averageRadius = (left_top.floatValue() + right_top.floatValue()
			+ left_bottom.floatValue() + right_bottom.floatValue()) / 4f;
			card.setRadius(averageRadius);
			
		} else {
			// Plain View (LinearLayout, etc.) — use GradientDrawable as before
			android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable();
			s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
			s.setCornerRadii(new float[] {
				left_top.floatValue(), left_top.floatValue(),
				right_top.floatValue(), right_top.floatValue(),
				left_bottom.floatValue(), left_bottom.floatValue(),
				right_bottom.floatValue(), right_bottom.floatValue()
			});
			s.setColor(resolvedColor);
			_view.setBackground(s);
		}
	}
	
	
	public void _TransitionManager(final View _view, final double _duration) {
		LinearLayout viewgroup =(LinearLayout) _view;
		
		android.transition.AutoTransition autoTransition = new android.transition.AutoTransition(); autoTransition.setDuration((long)_duration);
		autoTransition.setInterpolator(new android.view.animation.DecelerateInterpolator()); android.transition.TransitionManager.beginDelayedTransition(viewgroup, autoTransition);
	}
	
	
	public void _scrollToParentMessage(final String _parentMsgKey) {
		try {
			if (_parentMsgKey == null || _parentMsgKey.isEmpty()) {
				return;
			}
			
			int targetPos = -1;
			for (int i = 0; i < chat_list_map.size(); i++) {
				java.util.HashMap<String, Object> item = chat_list_map.get(i);
				if (item != null && _parentMsgKey.equals(String.valueOf(item.get("msg_key")))) {
					targetPos = i;
					break;
				}
			}
			
			if (targetPos != -1) {
				chat_list.smoothScrollToPosition(targetPos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public class Chat_listAdapter extends RecyclerView.Adapter<Chat_listAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Chat_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.chat_items, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout sep_date_holder = _view.findViewById(R.id.sep_date_holder);
			final LinearLayout sender = _view.findViewById(R.id.sender);
			final LinearLayout receiver = _view.findViewById(R.id.receiver);
			final LinearLayout typing_holder = _view.findViewById(R.id.typing_holder);
			final com.google.android.material.card.MaterialCardView date_sep = _view.findViewById(R.id.date_sep);
			final LinearLayout linear12 = _view.findViewById(R.id.linear12);
			final TextView date_sep_txt = _view.findViewById(R.id.date_sep_txt);
			final com.google.android.material.card.MaterialCardView sender_cd = _view.findViewById(R.id.sender_cd);
			final LinearLayout sender_bg = _view.findViewById(R.id.sender_bg);
			final LinearLayout sender_reply_holder = _view.findViewById(R.id.sender_reply_holder);
			final com.google.android.material.card.MaterialCardView ImagesHolder_cd = _view.findViewById(R.id.ImagesHolder_cd);
			final TextView sender_txt = _view.findViewById(R.id.sender_txt);
			final LinearLayout linear13 = _view.findViewById(R.id.linear13);
			final com.google.android.material.card.MaterialCardView sender_reply_cd = _view.findViewById(R.id.sender_reply_cd);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout sender_reply_sep = _view.findViewById(R.id.sender_reply_sep);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final TextView sender_reply_header = _view.findViewById(R.id.sender_reply_header);
			final TextView sender_reply_sub = _view.findViewById(R.id.sender_reply_sub);
			final LinearLayout image_card_inner = _view.findViewById(R.id.image_card_inner);
			final com.google.android.material.card.MaterialCardView sc1_cd = _view.findViewById(R.id.sc1_cd);
			final LinearLayout linear14 = _view.findViewById(R.id.linear14);
			final LinearLayout sec1 = _view.findViewById(R.id.sec1);
			final ImageView sc1_img = _view.findViewById(R.id.sc1_img);
			final com.google.android.material.card.MaterialCardView sc2_cd = _view.findViewById(R.id.sc2_cd);
			final com.google.android.material.card.MaterialCardView sc3_cd = _view.findViewById(R.id.sc3_cd);
			final LinearLayout linear15 = _view.findViewById(R.id.linear15);
			final ImageView sc2_img = _view.findViewById(R.id.sc2_img);
			final LinearLayout linear16 = _view.findViewById(R.id.linear16);
			final ImageView sc3_img = _view.findViewById(R.id.sc3_img);
			final TextView sender_time = _view.findViewById(R.id.sender_time);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			final com.google.android.material.card.MaterialCardView receiver_cd = _view.findViewById(R.id.receiver_cd);
			final LinearLayout receiver_bg = _view.findViewById(R.id.receiver_bg);
			final LinearLayout receiver_reply_holder = _view.findViewById(R.id.receiver_reply_holder);
			final TextView receiver_txt = _view.findViewById(R.id.receiver_txt);
			final TextView receiver_time = _view.findViewById(R.id.receiver_time);
			final com.google.android.material.card.MaterialCardView receiver_reply_cd = _view.findViewById(R.id.receiver_reply_cd);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout receiver_reply_sep = _view.findViewById(R.id.receiver_reply_sep);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final TextView receiver_reply_header = _view.findViewById(R.id.receiver_reply_header);
			final TextView receiver_reply_sub = _view.findViewById(R.id.receiver_reply_sub);
			final com.airbnb.lottie.LottieAnimationView lottie1 = _view.findViewById(R.id.lottie1);
			
			sender_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			sender_time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ritalic.ttf"), 0);
			receiver_txt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
			receiver_time.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ritalic.ttf"), 0);
			_Round(25, 25, 25, 0, " @color/sender_bg", sender_cd);
			_Round(25, 25, 2, 25, " @color/receiver_bg", receiver_cd);
			sender_reply_header.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 1);
			sender_reply_sub.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 1);
			receiver_reply_header.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 1);
			receiver_reply_sub.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 1);
			ImagesHolder_cd.setVisibility(View.GONE);
			try {
				java.util.HashMap<String, Object> currentMessage = _data.get((int)_position);
				
				String msgSenderId = "";
				String msgText = "";
				String msgTime = "";
				
				if (currentMessage != null) {
					boolean isTypingRow = "typing_row".equals(currentMessage.get("msg_key"));
					
					if (isTypingRow) {
						sender.setVisibility(View.GONE);
						receiver.setVisibility(View.GONE);
						typing_holder.setVisibility(View.VISIBLE);
						sep_date_holder.setVisibility(View.GONE);
					} else {
						typing_holder.setVisibility(View.GONE);
						
						msgSenderId = currentMessage.containsKey("sender_id")
						? String.valueOf(currentMessage.get("sender_id")) : "";
						msgText = currentMessage.containsKey("message")
						? String.valueOf(currentMessage.get("message")) : "";
						
						String fullDate = currentMessage.containsKey("date")
						? String.valueOf(currentMessage.get("date")) : "";
						
						java.text.SimpleDateFormat inputFmt = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
						java.text.SimpleDateFormat outputFmt = new java.text.SimpleDateFormat("hh:mm a");
						
						if (!fullDate.isEmpty()) {
							try {
								java.util.Date parsed = inputFmt.parse(fullDate);
								msgTime = outputFmt.format(parsed);
							} catch (Exception parseEx) {
								msgTime = fullDate;
							}
						}
						
						// ---- Date separator logic ----
						boolean showSeparator = true;
						String separatorLabel = "";
						
						try {
							if (!fullDate.isEmpty()) {
								java.util.Date msgDate = inputFmt.parse(fullDate);
								
								java.util.Calendar msgCal = java.util.Calendar.getInstance();
								msgCal.setTime(msgDate);
								msgCal.set(java.util.Calendar.HOUR_OF_DAY, 0);
								msgCal.set(java.util.Calendar.MINUTE, 0);
								msgCal.set(java.util.Calendar.SECOND, 0);
								msgCal.set(java.util.Calendar.MILLISECOND, 0);
								
								java.util.Calendar todayCal = java.util.Calendar.getInstance();
								todayCal.set(java.util.Calendar.HOUR_OF_DAY, 0);
								todayCal.set(java.util.Calendar.MINUTE, 0);
								todayCal.set(java.util.Calendar.SECOND, 0);
								todayCal.set(java.util.Calendar.MILLISECOND, 0);
								
								long diffMillis = todayCal.getTimeInMillis() - msgCal.getTimeInMillis();
								long diffDays = diffMillis / (24 * 60 * 60 * 1000);
								
								if (diffDays <= 0) {
									separatorLabel = "Today";
								} else if (diffDays == 1) {
									separatorLabel = "Yesterday";
								} else if (diffDays <= 10) {
									java.text.SimpleDateFormat weekdayFmt = new java.text.SimpleDateFormat("EEEE");
									separatorLabel = weekdayFmt.format(msgDate);
								} else {
									java.text.SimpleDateFormat longDateFmt = new java.text.SimpleDateFormat("MMMM d");
									separatorLabel = longDateFmt.format(msgDate);
								}
								
								// Compare with previous non-typing message to decide if separator should show
								if ((int) _position > 0) {
									java.util.HashMap<String, Object> prevMessage = _data.get((int)_position - 1);
									if (prevMessage != null && !"typing_row".equals(prevMessage.get("msg_key"))
									&& prevMessage.containsKey("date")) {
										String prevFullDate = String.valueOf(prevMessage.get("date"));
										try {
											java.util.Date prevDate = inputFmt.parse(prevFullDate);
											java.util.Calendar prevCal = java.util.Calendar.getInstance();
											prevCal.setTime(prevDate);
											prevCal.set(java.util.Calendar.HOUR_OF_DAY, 0);
											prevCal.set(java.util.Calendar.MINUTE, 0);
											prevCal.set(java.util.Calendar.SECOND, 0);
											prevCal.set(java.util.Calendar.MILLISECOND, 0);
											
											if (prevCal.getTimeInMillis() == msgCal.getTimeInMillis()) {
												showSeparator = false;
											}
										} catch (Exception prevEx) {
											prevEx.printStackTrace();
										}
									}
								}
							} else {
								showSeparator = false;
							}
						} catch (Exception sepEx) {
							sepEx.printStackTrace();
							showSeparator = false;
						}
						
						if (showSeparator) {
							sep_date_holder.setVisibility(View.VISIBLE);
							date_sep_txt.setText(separatorLabel);
						} else {
							sep_date_holder.setVisibility(View.GONE);
						}
						// ---- End date separator logic ----
						
						String myUid = FirebaseAuth.getInstance().getCurrentUser() != null
						? FirebaseAuth.getInstance().getCurrentUser().getUid() : "";
						
						boolean msgIsReply = currentMessage.containsKey("is_reply")
						&& Boolean.parseBoolean(String.valueOf(currentMessage.get("is_reply")));
						final String parentMsgText = currentMessage.containsKey("parent_msg")
						? String.valueOf(currentMessage.get("parent_msg")) : "";
						final String parentMsgKey = currentMessage.containsKey("parent_msg_key")
						? String.valueOf(currentMessage.get("parent_msg_key")) : "";
						
						if (msgSenderId.equals(myUid)) {
							sender.setVisibility(View.VISIBLE);
							receiver.setVisibility(View.GONE);
							sender_txt.setText(msgText);
							sender_time.setText(msgTime);
							
							if (msgIsReply) {
								sender_reply_holder.setVisibility(View.VISIBLE);
								sender_reply_sub.setText(parentMsgText);
							} else {
								sender_reply_holder.setVisibility(View.GONE);
							}
							
							sender_reply_cd.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									_scrollToParentMessage(parentMsgKey);
								}
							});
						} else {
							receiver.setVisibility(View.VISIBLE);
							sender.setVisibility(View.GONE);
							receiver_txt.setText(msgText);
							receiver_time.setText(msgTime);
							
							if (msgIsReply) {
								receiver_reply_holder.setVisibility(View.VISIBLE);
								receiver_reply_sub.setText(parentMsgText);
							} else {
								receiver_reply_holder.setVisibility(View.GONE);
							}
							
							receiver_reply_cd.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									_scrollToParentMessage(parentMsgKey);
								}
							});
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			sender_cd.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					
				}
			});
			receiver_bg.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					
				}
			});
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