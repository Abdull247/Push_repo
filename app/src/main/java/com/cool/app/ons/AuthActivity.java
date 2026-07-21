package com.error404.sudotalk;

import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import android.view.View;
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
import com.google.android.material.button.*;
import com.google.firebase.FirebaseApp;
import com.stdio.swipetoreply.*;
import eightbitlab.com.blurview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class AuthActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private RelativeLayout relativelayout1;
	private TextView textview3;
	private ImageView imageview1;
	private LinearLayout linear4;
	private TextView textview1;
	private MaterialButton str_btn;
	
	private Intent intent = new Intent();
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.auth);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		relativelayout1 = findViewById(R.id.relativelayout1);
		textview3 = findViewById(R.id.textview3);
		imageview1 = findViewById(R.id.imageview1);
		linear4 = findViewById(R.id.linear4);
		textview1 = findViewById(R.id.textview1);
		str_btn = findViewById(R.id.str_btn);
		dialog = new AlertDialog.Builder(this);
		
		str_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(AuthActivity.this, R.style.SudoTalkDialog);
				dialog.setTitle("Terms and Conditions");
				dialog.setMessage("By continuing you agree to our terms and conditions and you're completely okay about how we collect user data and information.");
				dialog.setPositiveButton("Agree", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						intent.setClass(getApplicationContext(), SignupActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
					}
				});
				dialog.setNeutralButton("Read T&C", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			final Window window = AuthActivity.this.getWindow();
			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			window.setStatusBarColor(getResources().getColor(R.color.background));
		}
		final float density = getResources().getDisplayMetrics().density;
		final float defaultRadius = 28f * density; // default (rounded)
		final float pressedRadius = 8f * density;  // pressed (squircle)
		
		final com.google.android.material.button.MaterialButton btn = str_btn;
		
		if (btn != null) {
			btn.setShapeAppearanceModel(btn.getShapeAppearanceModel().toBuilder().setAllCornerSizes(defaultRadius).build());
			
			btn.setOnTouchListener(new android.view.View.OnTouchListener() {
				private android.animation.ValueAnimator animator;
				
				@Override
				public boolean onTouch(final android.view.View v, android.view.MotionEvent event) {
					if (v.getParent() != null) {
						v.getParent().requestDisallowInterceptTouchEvent(true);
					}
					
					if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) {
						startAnim(pressedRadius, 100, new android.view.animation.DecelerateInterpolator());
					} else if (event.getAction() == android.view.MotionEvent.ACTION_UP || event.getAction() == android.view.MotionEvent.ACTION_CANCEL) {
						startAnim(defaultRadius, 300, new android.view.animation.AccelerateDecelerateInterpolator());
					}
					
					return false; // let the normal OnClickListener still fire
				}
				
				private void startAnim(float target, int duration, android.view.animation.Interpolator interpolator) {
					if (animator != null && animator.isRunning()) {
						animator.cancel();
					}
					
					android.graphics.RectF rect = new android.graphics.RectF(0, 0, btn.getWidth(), btn.getHeight());
					float startVal = btn.getShapeAppearanceModel().getBottomLeftCornerSize().getCornerSize(rect);
					
					animator = android.animation.ValueAnimator.ofFloat(startVal, target);
					animator.setDuration(duration);
					animator.setInterpolator(interpolator);
					
					animator.addUpdateListener(new android.animation.ValueAnimator.AnimatorUpdateListener() {
						@Override
						public void onAnimationUpdate(android.animation.ValueAnimator animation) {
							float value = (float) animation.getAnimatedValue();
							btn.setShapeAppearanceModel(btn.getShapeAppearanceModel().toBuilder().setAllCornerSizes(value).build());
						}
					});
					animator.start();
				}
			});
		}
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rmedium.ttf"), 1);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ooo.ttf"), 0);
		str_btn.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/rmono.ttf"), 1);
	}
	
}