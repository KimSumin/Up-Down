package kr.hs.emirim.space.updown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	ImageView btnStart, btnINfo;

	private BackPressCloseHandler backPressCloseHandler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		backPressCloseHandler = new BackPressCloseHandler(this);

		btnStart = (ImageView) findViewById(R.id.btn_start);
		btnINfo = (ImageView) findViewById(R.id.btn_info);

		btnStart.setOnClickListener(this);
		btnINfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		if (v.getId() == R.id.btn_start) {
			intent = new Intent(this, GameActivity.class);
			intent.putExtra("activityName", "main");

		} else if (v.getId() == R.id.btn_info) {
			intent = new Intent(this, InfoActivity.class);
		}
		finish();
		startActivity(intent);
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		backPressCloseHandler.onBackPressed();
	}
}