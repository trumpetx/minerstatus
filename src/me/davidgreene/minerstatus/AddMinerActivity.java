package me.davidgreene.minerstatus;

import me.davidgreene.minerstatus.util.MinerStatusConstants;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class AddMinerActivity extends AbstractMinerStatusActivity {
	
	private String poolToAdd;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addminer1);
        int bgColor = themeService.getTheme().getBackgroundColor();
        int color = themeService.getTheme().getTextColor();
        ScrollView scrollView = (ScrollView) findViewById(R.id.addMinerScrollView);
        scrollView.setBackgroundColor(bgColor);
    	TextView minerNameLabel = (TextView) findViewById(R.id.minerNameLabel);
    	minerNameLabel.setTextColor(color);
    	TextView minerDirections = (TextView) findViewById(R.id.minerDirections);
    	minerDirections.setTextColor(color);
    	RadioGroup rg = (RadioGroup) findViewById(R.id.addMinerRadioGroup);

	    for( String key : MinerStatusConstants.POOL_LABELS.keySet() ){
	    	String label = MinerStatusConstants.POOL_LABELS.get(key);
	    	RadioButton rb = new RadioButton(this);
	    	rb.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	    	rb.setTextColor(color);
	    	rb.setText(label);
	    	rb.setOnClickListener(radio_listener);
	    	rg.addView(rb);
	    }
	}
	    
	private OnClickListener radio_listener = new OnClickListener() {
	    public void onClick(View v) {
	        // Perform action on clicks
	        RadioButton rb = (RadioButton) v;
	        
		    for( String key : MinerStatusConstants.POOL_LABELS.keySet() ){
		    	String label = MinerStatusConstants.POOL_LABELS.get(key);
		    	if (label.equals(rb.getText().toString())){
		    		poolToAdd = key;
		    		break;
		    	}
		    }
	        
	    	final TextView minerNameLabel = (TextView) findViewById(R.id.minerNameLabel);
	    	final EditText minerName = (EditText) findViewById(R.id.minerName);	
	    	final Button addMinerButton = (Button) findViewById(R.id.addMinerButton);	
	    	final TextView minerDirections = (TextView) findViewById(R.id.minerDirections);	
	        
	        minerNameLabel.setVisibility(TextView.VISIBLE);
	        minerName.setVisibility(EditText.VISIBLE);	 
	        addMinerButton.setVisibility(Button.VISIBLE);
	        minerDirections.setVisibility(TextView.VISIBLE);	
	        if (poolToAdd.equals("bitcoinpool")){
	        	minerNameLabel.setText("Miner Name");
	        } else {
	        	minerNameLabel.setText("API Key");
	        }
	        minerDirections.setText(MinerStatusConstants.POOL_DIRECTIONS.get(poolToAdd));
	    }
	};
	
	public void addMiner(View view){
		final EditText minerName = (EditText) findViewById(R.id.minerName);	
		if (insertMiner(minerName.getText().toString(), poolToAdd)){
			Toast.makeText(getApplicationContext(), minerName.getText().toString()+"/"+poolToAdd+" added",
				Toast.LENGTH_LONG).show();			
			AddMinerActivity.this.finish();
		} else {
			Toast.makeText(getApplicationContext(), minerName.getText().toString()+"/"+poolToAdd+" is invalid",
					Toast.LENGTH_LONG).show();			
		}
	}
    
	private Boolean validateMiner(String miner, String pool){
		if (miner == null || miner.length() == 0){
			return Boolean.FALSE;
		}
		if (pool == null || pool.length() == 0){
			return Boolean.FALSE;
		}
		if(pool.equals("slush") || pool.equals("swepool")){
			for(Character c : miner.toCharArray()){
				if (!Character.isLetterOrDigit(c) && !c.equals('-')){
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		} else if(pool.equals("deepbit")){
			for(Character c : miner.toCharArray()){
				if (!Character.isLetterOrDigit(c) && !c.equals('_')){
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		} else if(pool.equals("bitclockers") || pool.equals("mtred") || pool.equals("btcmine") || pool.equals("btcguild") || pool.equals("bitcoinpool") || pool.equals("ozcoin")){
			for(Character c : miner.toCharArray()){
				if (!Character.isLetterOrDigit(c)){
					return Boolean.FALSE;
				}
			}
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	private Boolean insertMiner(String miner, String pool){
		if (validateMiner(miner, pool) && !minerService.minerExists(miner, pool)){
			minerService.insertMiner(miner, pool);
			return Boolean.TRUE;
		} else{
			Toast.makeText(getApplicationContext(), "Invalid Miner name: "+miner,
					Toast.LENGTH_LONG).show();
		}
		return Boolean.FALSE;
	}
    
}