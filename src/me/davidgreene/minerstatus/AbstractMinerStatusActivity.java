package me.davidgreene.minerstatus;

import me.davidgreene.minerstatus.service.ConfigService;
import me.davidgreene.minerstatus.service.MinerService;
import me.davidgreene.minerstatus.service.ThemeService;
import android.app.Activity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;

public abstract class AbstractMinerStatusActivity extends Activity {

	protected MinerService minerService; 
	protected ThemeService themeService;
	protected ConfigService configService;
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        minerService = ((MinerStatusApp)getApplication().getApplicationContext()).getMinerService();
        configService = ((MinerStatusApp)getApplication().getApplicationContext()).getConfigService();
        themeService = ((MinerStatusApp)getApplication().getApplicationContext()).getThemeService();
	}
	
	protected int getDip(float dipValue){
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 
                (float) dipValue, getResources().getDisplayMetrics());
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        return true;
	    } else {
	    	return super.onKeyDown(keyCode, event);
	    }
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	        finish();
	    }
	    return super.onKeyUp(keyCode, event);
	}
}
