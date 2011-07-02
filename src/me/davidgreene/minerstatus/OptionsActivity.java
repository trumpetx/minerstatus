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
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        int bgColor = themeService.getTheme().getBackgroundColor();
        int color = themeService.getTheme().getTextColor();
        LinearLayout layout = (LinearLayout) findViewById(R.id.optionsLayout);
        ScrollView scrollView = (ScrollView) findViewById(R.id.optionsScrollView);
        scrollView.setBackgroundColor(bgColor);
        
        TextView[] textViews = {(TextView) findViewById(R.id.themeSpinnerLabel),
        						(TextView) findViewById(R.id.connectionTimeoutLabel),
        						(TextView) findViewById(R.id.maxErrorsLabel)};
        for (TextView tv : textViews){
        	tv.setTextColor(color);
        }
        
	    for( final String key : EXCHANGE_URLS.keySet() ){
	    	final ToggleButton toggle = new ToggleButton(OptionsActivity.this);
	    	LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	    	params.setMargins(20, 0, 0, 15);
	    	toggle.setLayoutParams(params);
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
			        Toast.makeText(OptionsActivity.this, (toggle.isChecked()) ? EXCHANGE_LABELS.get(key)+" Visible" : EXCHANGE_LABELS.get(key)+" Hidden", Toast.LENGTH_SHORT).show();
				}
			});
	    	TextView tv = new TextView(OptionsActivity.this);
	    	tv.setText("Toggle "+ EXCHANGE_LABELS.get(key)+" Visibility:");
	    	tv.setLayoutParams(params);
	    	tv.setTextColor(color);
	    	layout.addView(toggle, 0);
	    	layout.addView(tv, 0);
	    	
	    }   
        
        final NumberPicker connectionTimeoutPicker = (NumberPicker) findViewById(R.id.connectionTimeoutPicker);
        Integer currentVal;
        try {
        	currentVal = Integer.valueOf(configService.getConfigValue("connection.timeout")) / 1000;
        } catch (NumberFormatException e) {
        	currentVal = CONNECTION_TIMEOUT / 1000;
		}
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
        Integer currentErrorVal;
        try {
        	currentErrorVal = Integer.valueOf(configService.getConfigValue("max.errors"));
        } catch (NumberFormatException e) {
        	currentErrorVal = MAX_ERRORS;
		}
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
    	lowHashrate.setText(configService.getConfigValue("low.hashrate.notification"));
        Button lowHashrateButton = (Button) findViewById(R.id.lowHashrateSaveButton);
        lowHashrateButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
				EditText lowHashrate = (EditText) findViewById(R.id.lowHashrateInput);
				int lowHashrateInt;
				try{
					lowHashrateInt = Integer.valueOf(lowHashrate.getText().toString());
					if (lowHashrateInt < -1){
						lowHashrateInt = -1;
					}
				} catch (Exception e){
					lowHashrateInt = -1;
				}
				lowHashrate.setText(String.valueOf(lowHashrateInt));
				configService.setConfigValue("low.hashrate.notification", String.valueOf(lowHashrateInt));
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
