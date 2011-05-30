package me.davidgreene.minerstatus;

import static me.davidgreene.minerstatus.util.MinerStatusConstants.POOL_DIRECTIONS;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
        
    	List<RadioButton> radioList = new LinkedList<RadioButton>();
    	radioList.add((RadioButton)findViewById(R.id.radio_bitcoinpool));
    	radioList.add((RadioButton) findViewById(R.id.radio_slush));
    	radioList.add((RadioButton) findViewById(R.id.radio_deepbit));
    	radioList.add((RadioButton) findViewById(R.id.radio_btcmine));
    	radioList.add((RadioButton) findViewById(R.id.radio_btcguild));
    	radioList.add((RadioButton) findViewById(R.id.radio_bitclockers));
    	radioList.add((RadioButton) findViewById(R.id.radio_swepool));
    	radioList.add((RadioButton) findViewById(R.id.radio_mtred));

    	for(RadioButton radio : radioList){
    		radio.setOnClickListener(radio_listener);
    		radio.setTextColor(color);    		
    	}
    }
	
	private OnClickListener radio_listener = new OnClickListener() {
	    public void onClick(View v) {
	        // Perform action on clicks
	        RadioButton rb = (RadioButton) v;
	    	final TextView minerNameLabel = (TextView) findViewById(R.id.minerNameLabel);
	    	final EditText minerName = (EditText) findViewById(R.id.minerName);	
	    	final Button addMinerButton = (Button) findViewById(R.id.addMinerButton);	
	    	final TextView minerDirections = (TextView) findViewById(R.id.minerDirections);	
	        if(rb.getText().equals("Bitcoin Pool")){
	        	poolToAdd = "bitcoinpool";
	        	minerNameLabel.setText("Miner Name");
	        } else if (rb.getText().equals("Slush's Pool")){
	        	poolToAdd = "slush";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("Deepbit.net")){
	        	poolToAdd = "deepbit";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("BtcMine")){
	        	poolToAdd = "btcmine";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("Btcguild")){
	        	poolToAdd = "btcguild";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("Bitclockers")){
	        	poolToAdd = "bitclockers";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("Swepool")){
	        	poolToAdd = "swepool";
	        	minerNameLabel.setText("API Key");
	        } else if (rb.getText().equals("Mt. Red")){
	        	poolToAdd = "mtred";
	        	minerNameLabel.setText("API Key");
	        }
	        minerNameLabel.setVisibility(TextView.VISIBLE);
	        minerName.setVisibility(EditText.VISIBLE);	 
	        addMinerButton.setVisibility(Button.VISIBLE);
	        minerDirections.setVisibility(TextView.VISIBLE);	
	        minerDirections.setText(POOL_DIRECTIONS.get(poolToAdd));
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
		} else if(pool.equals("bitclockers") || pool.equals("mtred") || pool.equals("btcmine") || pool.equals("btcguild") || pool.equals("bitcoinpool")){
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