package kr.hs.emirim.space.updown;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends Activity implements OnClickListener {

	ImageView btnExit;
	LinearLayout background;
	boolean info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_info);

		btnExit = (ImageView) findViewById(R.id.btn_exitInfo);
		background = (LinearLayout) findViewById(R.id.container);

		btnExit.setOnClickListener(this);
		background.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.btn_exitInfo) {
			finish();
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		} else if (v.getId() == R.id.container) {
			if (info) {
				background.setBackgroundResource(R.drawable.info_01);
				info = false;
			} else {
				background.setBackgroundResource(R.drawable.info_02);
				info = true;
			}

		}
	}

}
