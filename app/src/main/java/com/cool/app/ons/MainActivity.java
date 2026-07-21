package com.error404.sudotalk;

import com.error404.sudotalk.SplashActivity;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
import android.content.res.*;
import android.graphics.*;
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


public class MainActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> status = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> chat_list = new ArrayList<>();
	
	private LinearLayout main;
	private FrameLayout appFrame;
	
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
	private DatabaseReference user_status = _firebase.getReference("user_status");
	private ChildEventListener _user_status_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		main = findViewById(R.id.main);
		appFrame = findViewById(R.id.appFrame);
		auth = FirebaseAuth.getInstance();
		
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
		// 1. Tell the window to draw content behind the system bars
		androidx.core.view.WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
		
		// 2. Make the status bar fully transparent
		getWindow().setStatusBarColor(android.graphics.Color.TRANSPARENT);
		
		// 3. Detect if the system is in Dark Mode or Light Mode
		int nightModeFlags = getResources().getConfiguration().uiMode & android.content.res.Configuration.UI_MODE_NIGHT_MASK;
		
		androidx.core.view.WindowInsetsControllerCompat windowInsetsController = 
		androidx.core.view.WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
		
		if (windowInsetsController != null) {
			if (nightModeFlags == android.content.res.Configuration.UI_MODE_NIGHT_YES) {
				windowInsetsController.setAppearanceLightStatusBars(false);
			} else {
				windowInsetsController.setAppearanceLightStatusBars(true);
			}
		}
		
		final View bottomBarView = getLayoutInflater().inflate(R.layout.bottom_bar, null);
		
		final com.google.android.material.card.MaterialCardView it_cd1 = (com.google.android.material.card.MaterialCardView) bottomBarView.findViewById(R.id.it_cd1);
		final com.google.android.material.card.MaterialCardView it_cd2 = (com.google.android.material.card.MaterialCardView) bottomBarView.findViewById(R.id.it_cd2);
		final com.google.android.material.card.MaterialCardView it_cd3 = (com.google.android.material.card.MaterialCardView) bottomBarView.findViewById(R.id.it_cd3);
		final ImageView it_img1 = (ImageView) bottomBarView.findViewById(R.id.it_img1);
		final ImageView it_img2 = (ImageView) bottomBarView.findViewById(R.id.it_img2);
		final ImageView it_img3 = (ImageView) bottomBarView.findViewById(R.id.it_img3);
		final TextView it_txt1 = (TextView) bottomBarView.findViewById(R.id.it_txt1);
		final TextView it_txt2 = (TextView) bottomBarView.findViewById(R.id.it_txt2);
		final TextView it_txt3 = (TextView) bottomBarView.findViewById(R.id.it_txt3);
		
		it_txt1.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), 1);
		it_txt2.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), 1);
		it_txt3.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), 1);
		
		ViewGroup activityContainer = (ViewGroup) findViewById(android.R.id.content);
		
		FrameLayout.LayoutParams bottomBarParams = new FrameLayout.LayoutParams(
		FrameLayout.LayoutParams.MATCH_PARENT,
		FrameLayout.LayoutParams.WRAP_CONTENT
		);
		bottomBarParams.gravity = android.view.Gravity.BOTTOM;
		bottomBarView.setLayoutParams(bottomBarParams);
		
		activityContainer.addView(bottomBarView);
		
		final View fabView = getLayoutInflater().inflate(R.layout.cus_fab, null);
		final com.google.android.material.card.MaterialCardView fb_1 = (com.google.android.material.card.MaterialCardView) fabView.findViewById(R.id.fb_1);
		final com.google.android.material.card.MaterialCardView fb_2 = (com.google.android.material.card.MaterialCardView) fabView.findViewById(R.id.fb_2);
		final ImageView fb1_img = (ImageView) fabView.findViewById(R.id.fb1_img);
		
		final FrameLayout.LayoutParams fabParams = new FrameLayout.LayoutParams(
		FrameLayout.LayoutParams.WRAP_CONTENT,
		FrameLayout.LayoutParams.WRAP_CONTENT
		);
		fabParams.gravity = android.view.Gravity.BOTTOM | android.view.Gravity.END;
		fabView.setLayoutParams(fabParams);
		
		final View customProfileBtnView = getLayoutInflater().inflate(R.layout.st_btn, null);
		final com.google.android.material.card.MaterialCardView st_inner_cd = (com.google.android.material.card.MaterialCardView) customProfileBtnView.findViewById(R.id.inner_cd);
		final TextView cd_txt = (TextView) customProfileBtnView.findViewById(R.id.cd_txt);
		
		FrameLayout.LayoutParams stBtnParams = new FrameLayout.LayoutParams(
		FrameLayout.LayoutParams.MATCH_PARENT,
		FrameLayout.LayoutParams.WRAP_CONTENT
		);
		stBtnParams.gravity = android.view.Gravity.BOTTOM | android.view.Gravity.CENTER_HORIZONTAL;
		customProfileBtnView.setLayoutParams(stBtnParams);
		
		customProfileBtnView.setVisibility(View.GONE);
		activityContainer.addView(customProfileBtnView);
		cd_txt.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/ooo.ttf"), 0);
		
		st_inner_cd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SketchwareUtil.showMessage(getApplicationContext(), "Add a post clicked!");
			}
		});
		
		fb_1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), NewMsgActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});
		
		fb_2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				// TODO: camera FAB clicked
			}
		});
		
		if (activityContainer != null) {
			activityContainer.addView(fabView);
		}
		
		bottomBarView.post(new Runnable() {
			@Override
			public void run() {
				int barHeight = bottomBarView.getHeight();
				int marginPx = (int) (4 * getResources().getDisplayMetrics().density);
				fabParams.bottomMargin = barHeight + marginPx;
				fabView.setLayoutParams(fabParams);
			}
		});
		
		// Fragment instances — created once, never recreated on tab switch
		final ChatsFragFragmentActivity chatsFragment = new ChatsFragFragmentActivity();
		final ExploreFragFragmentActivity exploreFragment = new ExploreFragFragmentActivity();
		final ProfileFragFragmentActivity profileFragment = new ProfileFragFragmentActivity();
		
		// Load all three upfront, show only chats by default
		getSupportFragmentManager().beginTransaction()
		.add(R.id.appFrame, chatsFragment, "chats")
		.add(R.id.appFrame, exploreFragment, "explore")
		.add(R.id.appFrame, profileFragment, "profile")
		.hide(exploreFragment)
		.hide(profileFragment)
		.commit();
		
		// Colors and duration — declared once
		final int colorPrimary = getResources().getColor(R.color.primary);
		final int colorBackBg = getResources().getColor(R.color.back_bg);
		final int colorTextHeader = getResources().getColor(R.color.text_header);
		final int colorTransparent = Color.parseColor("#00000000");
		final int animDuration = 250;
		
		it_cd1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int currentBg1 = it_cd1.getCardBackgroundColor().getDefaultColor();
				int currentBg2 = it_cd2.getCardBackgroundColor().getDefaultColor();
				int currentBg3 = it_cd3.getCardBackgroundColor().getDefaultColor();
				int currentText1 = it_txt1.getCurrentTextColor();
				int currentText2 = it_txt2.getCurrentTextColor();
				int currentText3 = it_txt3.getCurrentTextColor();
				
				ValueAnimator bg1 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg1, colorBackBg);
				bg1.setDuration(animDuration);
				bg1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd1.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg1.start();
				
				ValueAnimator color1 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText1, colorPrimary);
				color1.setDuration(animDuration);
				color1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt1.setTextColor(c);
						it_img1.setColorFilter(c);
					}
				});
				color1.start();
				
				it_img1.setImageResource(R.drawable.chat_selected);
				
				ValueAnimator bg2 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg2, colorTransparent);
				bg2.setDuration(animDuration);
				bg2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd2.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg2.start();
				
				ValueAnimator color2 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText2, colorTextHeader);
				color2.setDuration(animDuration);
				color2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt2.setTextColor(c);
						it_img2.setColorFilter(c);
					}
				});
				color2.start();
				
				it_img2.setImageResource(R.drawable.explore_unselected);
				
				ValueAnimator bg3 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg3, colorTransparent);
				bg3.setDuration(animDuration);
				bg3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd3.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg3.start();
				
				ValueAnimator color3 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText3, colorTextHeader);
				color3.setDuration(animDuration);
				color3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt3.setTextColor(c);
						it_img3.setColorFilter(c);
					}
				});
				color3.start();
				
				it_img3.setImageResource(R.drawable.account_unselected);
				
				getSupportFragmentManager().beginTransaction()
				.show(chatsFragment)
				.hide(exploreFragment)
				.hide(profileFragment)
				.commit();
				
				fb_2.animate().cancel();
				fb_1.animate().cancel();
				fb_2.setVisibility(View.VISIBLE);
				fb_2.setAlpha(1f);
				fb_2.setTranslationY(0f);
				fb_1.setVisibility(View.VISIBLE);
				fb_1.setAlpha(1f);
				fb_1.setTranslationY(0f);
				fb1_img.setImageResource(R.drawable.new_message);
				
				customProfileBtnView.setVisibility(View.GONE);
			}
		});
		
		it_cd2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int currentBg1 = it_cd1.getCardBackgroundColor().getDefaultColor();
				int currentBg2 = it_cd2.getCardBackgroundColor().getDefaultColor();
				int currentBg3 = it_cd3.getCardBackgroundColor().getDefaultColor();
				int currentText1 = it_txt1.getCurrentTextColor();
				int currentText2 = it_txt2.getCurrentTextColor();
				int currentText3 = it_txt3.getCurrentTextColor();
				
				ValueAnimator bg1 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg1, colorTransparent);
				bg1.setDuration(animDuration);
				bg1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd1.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg1.start();
				
				ValueAnimator color1 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText1, colorTextHeader);
				color1.setDuration(animDuration);
				color1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt1.setTextColor(c);
						it_img1.setColorFilter(c);
					}
				});
				color1.start();
				
				it_img1.setImageResource(R.drawable.chat_unselected);
				
				ValueAnimator bg2 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg2, colorBackBg);
				bg2.setDuration(animDuration);
				bg2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd2.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg2.start();
				
				ValueAnimator color2 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText2, colorPrimary);
				color2.setDuration(animDuration);
				color2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt2.setTextColor(c);
						it_img2.setColorFilter(c);
					}
				});
				color2.start();
				
				it_img2.setImageResource(R.drawable.explore_selected);
				
				ValueAnimator bg3 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg3, colorTransparent);
				bg3.setDuration(animDuration);
				bg3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd3.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg3.start();
				
				ValueAnimator color3 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText3, colorTextHeader);
				color3.setDuration(animDuration);
				color3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt3.setTextColor(c);
						it_img3.setColorFilter(c);
					}
				});
				color3.start();
				
				it_img3.setImageResource(R.drawable.account_unselected);
				
				getSupportFragmentManager().beginTransaction()
				.hide(chatsFragment)
				.show(exploreFragment)
				.hide(profileFragment)
				.commit();
				
				fb_2.animate().cancel();
				fb_1.animate().cancel();
				fb_1.setVisibility(View.VISIBLE);
				fb_1.setAlpha(1f);
				fb_1.setTranslationY(0f);
				fb1_img.setImageResource(R.drawable.add_img);
				fb_2.animate()
				.translationY(fb_2.getHeight() + 100)
				.alpha(0f)
				.setDuration(200)
				.setStartDelay(0)
				.withEndAction(new Runnable() {
					@Override
					public void run() {
						fb_2.setVisibility(View.GONE);
					}
				})
				.start();
				
				customProfileBtnView.setVisibility(View.GONE);
			}
		});
		
		it_cd3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int currentBg1 = it_cd1.getCardBackgroundColor().getDefaultColor();
				int currentBg2 = it_cd2.getCardBackgroundColor().getDefaultColor();
				int currentBg3 = it_cd3.getCardBackgroundColor().getDefaultColor();
				int currentText1 = it_txt1.getCurrentTextColor();
				int currentText2 = it_txt2.getCurrentTextColor();
				int currentText3 = it_txt3.getCurrentTextColor();
				
				ValueAnimator bg1 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg1, colorTransparent);
				bg1.setDuration(animDuration);
				bg1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd1.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg1.start();
				
				ValueAnimator color1 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText1, colorTextHeader);
				color1.setDuration(animDuration);
				color1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt1.setTextColor(c);
						it_img1.setColorFilter(c);
					}
				});
				color1.start();
				
				it_img1.setImageResource(R.drawable.chat_unselected);
				
				ValueAnimator bg2 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg2, colorTransparent);
				bg2.setDuration(animDuration);
				bg2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd2.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg2.start();
				
				ValueAnimator color2 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText2, colorTextHeader);
				color2.setDuration(animDuration);
				color2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt2.setTextColor(c);
						it_img2.setColorFilter(c);
					}
				});
				color2.start();
				
				it_img2.setImageResource(R.drawable.explore_unselected);
				
				ValueAnimator bg3 = ValueAnimator.ofObject(new ArgbEvaluator(), currentBg3, colorBackBg);
				bg3.setDuration(animDuration);
				bg3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						it_cd3.setCardBackgroundColor((Integer) animation.getAnimatedValue());
					}
				});
				bg3.start();
				
				ValueAnimator color3 = ValueAnimator.ofObject(new ArgbEvaluator(), currentText3, colorPrimary);
				color3.setDuration(animDuration);
				color3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
					@Override
					public void onAnimationUpdate(ValueAnimator animation) {
						int c = (Integer) animation.getAnimatedValue();
						it_txt3.setTextColor(c);
						it_img3.setColorFilter(c);
					}
				});
				color3.start();
				
				it_img3.setImageResource(R.drawable.account_selected);
				
				getSupportFragmentManager().beginTransaction()
				.hide(chatsFragment)
				.hide(exploreFragment)
				.show(profileFragment)
				.commit();
				
				fb_2.animate().cancel();
				fb_1.animate().cancel();
				fb_2.animate()
				.translationY(fb_2.getHeight() + 100)
				.alpha(0f)
				.setDuration(200)
				.withEndAction(new Runnable() {
					@Override
					public void run() {
						fb_2.setVisibility(View.GONE);
					}
				})
				.start();
				
				fb_1.animate()
				.translationY(fb_1.getHeight() + 100)
				.alpha(0f)
				.setDuration(200)
				.setStartDelay(100)
				.withEndAction(new Runnable() {
					@Override
					public void run() {
						fb_1.setVisibility(View.GONE);
					}
				})
				.start();
				
				customProfileBtnView.setVisibility(View.VISIBLE);
			}
		});
		status = new HashMap<>();
		status.put("status", "online");
		user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
		status.clear();
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		status = new HashMap<>();
		status.put("status", "online");
		user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
		status.clear();
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		status = new HashMap<>();
		status.put("status", "online");
		user_status.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(status);
		status.clear();
	}
}