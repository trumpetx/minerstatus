package me.davidgreene.minerstatus;

import static me.davidgreene.minerstatus.util.MinerStatusConstants.CONNECTION_TIMEOUT;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.EXCHANGE_LABELS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.EXCHANGE_URLS;
import static me.davidgreene.minerstatus.util.MinerStatusConstants.MAX_ERRORS;
import me.davidgreene.minerstatus.util.NumberPicker;
import me.davidgreene.minerstatus.util.NumberPicker.OnChangedListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class OptionsActivity extends AbstractMinerStatusActivity {
	
	private static final LayoutParams TOGGLE_PARAMS;
	static {
		TOGGLE_PARAMS = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		TOGGLE_PARAMS.setMargins(20, 0, 0, 15);
	}
	
	private ToggleButton generateToggleButton(final String key, final String textOn, final String textOff){
    	final ToggleButton toggle = new ToggleButton(OptionsActivity.this);
    	toggle.setLayoutParams(TOGGLE_PARAMS);
    	toggle.setTextOn("Visible");
    	toggle.setTextOff("Hidden");
    	toggle.setChecked(Boolean.valueOf(configService.getConfigValue("show."+key)));
    	toggle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
		        if (toggle.isChecked()) {
		        	configService.setConfigValue("show."+key, "true");
		        } else {
		        	configService.setConfigValue("show."+key, "false");
		        }
		        Toast.makeText(OptionsActivity.this, (toggle.isChecked()) ? textOn : textOff, Toast.LENGTH_SHORT).show();
			}
		});
		return toggle;
	}
	
	private TextView generateTextViewForToggleButton(String label){
		TextView tv = new TextView(OptionsActivity.this);
    	tv.setText(label);
    	tv.setLayoutParams(TOGGLE_PARAMS);
    	tv.setTextColor(color);
    	
    	return tv;
	}
	
	private int color;
	private int bgColor;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        bgColor = themeService.getTheme().getBackgroundColor();
        color = themeService.getTheme().getTextColor();
        LinearLayout layout = (LinearLayout) findViewById(R.id.optionsLayout);
        layout.setFocusable(true);
        ScrollView scrollView = (ScrollView) findViewById(R.id.optionsScrollView);
        scrollView.setFocusable(true);
        scrollView.setBackgroundColor(bgColor);
        
        for (TextView tv : new TextView[] { (TextView) findViewById(R.id.themeSpinnerLabel),
											(TextView) findViewById(R.id.connectionTimeoutLabel),
											(TextView) findViewById(R.id.maxErrorsLabel) }){
        	tv.setTextColor(color);
        }
        
        layout.addView(generateToggleButton("ads", "Thanks for supporting MinerStatus!", "No ads for you!"), 0);
        layout.addView(generateTextViewForToggleButton("Toggle Ad Visibility:"), 0);
	    
	    for( final String key : EXCHANGE_URLS.keySet() ){
	    	layout.addView(generateToggleButton(key, EXCHANGE_LABELS.get(key)+" Visible", EXCHANGE_LABELS.get(key)+" Hidden"), 0);
	    	layout.addView(generateTextViewForToggleButton("Toggle "+ EXCHANGE_LABELS.get(key)+" Visibility:"), 0);
	    }  
	        	
        final NumberPicker connectionTimeoutPicker = (NumberPicker) findViewById(R.id.connectionTimeoutPicker);
        int currentVal = CONNECTION_TIMEOUT / 1000;
        try {
        	currentVal = Integer.parseInt(configService.getConfigValue("connection.timeout")) / 1000;
        } catch (NumberFormatException e) { /* leave default */ }
        connectionTimeoutPicker.setCurrent(currentVal);
        connectionTimeoutPicker.setOnChangeListener(new OnChangedListener() {
			@Override
			public void onChanged(NumberPicker picker, int oldVal, int newVal) {
				if (newVal > 0){
					configService.setConfigValue("connection.timeout", String.valueOf(newVal * 1000));
				} else {
					configService.setConfigValue("connection.timeout", String.valueOf(0));
				}
			}
		});
        
        final NumberPicker maxErrorsPicker = (NumberPicker) findViewById(R.id.maxErrorsPicker);
        int currentErrorVal = MAX_ERRORS;
        try {
        	currentErrorVal = Integer.parseInt(configService.getConfigValue("max.errors"));
        } catch (NumberFormatException e) { /* leave default */ }
        
        maxErrorsPicker.setCurrent(currentErrorVal);
        maxErrorsPicker.setOnChangeListener(new OnChangedListener() {
			@Override
			public void onChanged(NumberPicker picker, int oldVal, int newVal) {
				if (newVal >= 0){
					configService.setConfigValue("max.errors", String.valueOf(newVal));
				} else {
					configService.setConfigValue("max.errors", String.valueOf(MAX_ERRORS));
				}
			}
		});  
        
    	final RadioButton darkTheme = (RadioButton) findViewById(R.id.radio_dark);
    	final RadioButton lightTheme = (RadioButton) findViewById(R.id.radio_light);

    	String themeString = configService.getConfigValue("theme");
    	
    	if(themeString.equals("light")){
    		lightTheme.setChecked(Boolean.TRUE);
    	} else if (themeString.equals("dark")){
    		darkTheme.setChecked(Boolean.TRUE);
    	}
    	
    	darkTheme.setOnClickListener(radioListener);
    	darkTheme.setTextColor(color);
    	
    	lightTheme.setOnClickListener(radioListener);
    	lightTheme.setTextColor(color);
    	
    	TextView widgetSpinnerLabel = (TextView) findViewById(R.id.widgetSpinnerLabel);
    	widgetSpinnerLabel.setTextColor(color);
    	
    	
    	final Spinner widgetSpinner = (Spinner) findViewById(R.id.widget_spinner);
    	final int defaultSelection = populateWidgetSpinner(widgetSpinner, configService.getConfigValue("widget.apiKey"));
    	widgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

    		private boolean initialized = false;
    		
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				if (!initialized){
					widgetSpinner.setSelection(defaultSelection);
					initialized = true;
					return;
				}
				Spinner spinner = (Spinner) arg0;
				ArrayAdapter arrayAdapter = (ArrayAdapter) spinner.getAdapter();
				String apiKey = (String) arrayAdapter.getItem(position);
				configService.setConfigValue("widget.apiKey", apiKey);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				configService.setConfigValue("widget.apiKey", "none");
			}
    		
		});

    	TextView lowHashrateLabel = (TextView) findViewById(R.id.lowHashrateNotificationLabel);
    	lowHashrateLabel.setTextColor(color);
    	EditText lowHashrate = (EditText) findViewById(R.id.lowHashrateInput);
    	String lowHashrateValue = configService.getConfigValue("low.hashrate.notification");
    	lowHashrate.setText(lowHashrateValue);
    	lowHashrate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				EditText view = (EditText) v;
				if(hasFocus && "off".equals(configService.getConfigValue("low.hashrate.notification"))){
					view.setText("");
				}
			}
		});
    	
    	Button lowHashrateButton = (Button) findViewById(R.id.lowHashrateSaveButton);
    	lowHashrateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText lowHashrate = (EditText) findViewById(R.id.lowHashrateInput);
				int lowHashrateInt = 0;
				String configValue = "off";
				try{
					lowHashrateInt = Integer.parseInt(lowHashrate.getText().toString());
					if (lowHashrateInt >= 0){
						configValue = String.valueOf(lowHashrateInt);
					}
				} catch (NumberFormatException e){ /* Leave value "off" */ }
				
				lowHashrate.setText(configValue);
				configService.setConfigValue("low.hashrate.notification", configValue);
				Toast.makeText(getApplicationContext(), "Low Hashrate set to: "+configValue, Toast.LENGTH_LONG).show();
			}
		});

    	TextView minerDeleteSpinnerLabel = (TextView) findViewById(R.id.deleteSpinnerLabel);
    	minerDeleteSpinnerLabel.setTextColor(color);
    	
    	final Spinner spinner = (Spinner) findViewById(R.id.miner_delete_spinner);
    	populateDeleteSpinner(spinner);
        Button deleteMinerButton = (Button) findViewById(R.id.deleteMinerButtonOptionsMenu);
        deleteMinerButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if (spinner.getSelectedItem() == null){
						Toast.makeText(getApplicationContext(), "You cannot delete nothing!?  Or can you?",
								Toast.LENGTH_LONG).show();
						return;
					}
					AlertDialog.Builder alert = new AlertDialog.Builder(OptionsActivity.this);
					alert.setTitle("Remove " + (CharSequence)spinner.getSelectedItem() + "?");
					alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int whichButton) {
							Toast.makeText(getApplicationContext(), (CharSequence)spinner.getSelectedItem() +" removed.",
									Toast.LENGTH_LONG).show();
							minerService.deleteMiner(((CharSequence)spinner.getSelectedItem()).toString());
							populateDeleteSpinner(spinner);
						}
					});		
					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int whichButton) {
							dialog.cancel();
						}
					});				
					alert.show();  
				}
			});
        
        
        
	}	
	
	private void populateDeleteSpinner(Spinner spinner){
    	Cursor cur = minerService.getMiners();
    	ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		while(cur.moveToNext()){
			String miner = cur.getString(0);     
			CharSequence seq = miner;
			adapter.add(miner);
		}
		spinner.setAdapter(adapter);
		cur.close();
	}
	
	
	private int populateWidgetSpinner(Spinner spinner, String defaultSelection){
    	Cursor cur = minerService.getMiners();
    	ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	adapter.add("none");
    	int position = 0;
    	int i = 1;
		while(cur.moveToNext()){
			String miner = cur.getString(0);     
			CharSequence seq = miner;
			adapter.add(miner);
			if (miner.equals(defaultSelection)){
				position = i; 
			}
			i++;
		}
		spinner.setAdapter(adapter);
		cur.close();
		return position;
	}
	
	private OnClickListener radioListener = new OnClickListener() {
	    public void onClick(View v) {
	        RadioButton rb = (RadioButton) v;
	        if(rb.getText().equals("Dark Theme")){
	        	configService.setConfigValue("theme", "dark");
	        } else if (rb.getText().equals("Light Theme")){
	        	configService.setConfigValue("theme", "light");
	        }
	    }
	};

	
}
