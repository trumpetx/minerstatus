package me.davidgreene.minerstatus;

import java.math.BigDecimal;

import me.davidgreene.minerstatus.beans.BitclockersStatus;
import me.davidgreene.minerstatus.beans.BitclockersWorker;
import me.davidgreene.minerstatus.beans.BitpoolStatus;
import me.davidgreene.minerstatus.beans.BtcMine;
import me.davidgreene.minerstatus.beans.BtcMineWorker;
import me.davidgreene.minerstatus.beans.BtcguildStatus;
import me.davidgreene.minerstatus.beans.BtcguildWorker;
import me.davidgreene.minerstatus.beans.DeepbitStatus;
import me.davidgreene.minerstatus.beans.MtredStatus;
import me.davidgreene.minerstatus.beans.MtredWorker;
import me.davidgreene.minerstatus.beans.SlushStatus;
import me.davidgreene.minerstatus.beans.SlushWorker;
import me.davidgreene.minerstatus.beans.Status;
import me.davidgreene.minerstatus.beans.SwepoolStatus;
import me.davidgreene.minerstatus.beans.SwepoolWorker;
import me.davidgreene.minerstatus.beans.WorkerStatus;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ViewMinerActivity extends AbstractMinerStatusActivity {

	//private static final String tag = "TX";
	
	private Status minerStatus;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        minerStatus = (Status) bundle.getSerializable("status");
        setContentView(R.layout.viewminer);
        int bgColor = themeService.getTheme().getBackgroundColor();
        ScrollView scrollView = (ScrollView) findViewById(R.id.viewMinerScrollView);
        scrollView.setBackgroundColor(bgColor);
        
        try{
        	populateDetailedView();
        } catch (final NullPointerException e){
			AlertDialog.Builder alert = new AlertDialog.Builder(ViewMinerActivity.this);
			alert.setTitle("MinerStatus broke something!");
			alert.setPositiveButton("Ignore & Continue", new DialogInterface.OnClickListener() {	
				public void onClick(DialogInterface dialog, int whichButton) {
					Toast.makeText(getApplicationContext(), "I ate the error for you (YUM).  If you would like to help debug MinerStatus, throw the Exception once and make sure you report it.  Thanks!",
							Toast.LENGTH_LONG).show();
				}
			});		
			alert.setNegativeButton("Throw Exception", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {
					throw e;
				}
			});				
			alert.show();  
        }
        
        Button deleteMinerButton = (Button) findViewById(R.id.deleteMinerButton);
        deleteMinerButton.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					AlertDialog.Builder alert = new AlertDialog.Builder(ViewMinerActivity.this);
					alert.setTitle(minerStatus.getApiKey());
					alert.setPositiveButton("Remove", new DialogInterface.OnClickListener() {	
						public void onClick(DialogInterface dialog, int whichButton) {
							Toast.makeText(getApplicationContext(), minerStatus.getApiKey()+" removed.",
									Toast.LENGTH_LONG).show();
							minerService.deleteMiner(minerStatus.getApiKey());
							ViewMinerActivity.this.finish();
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
	
	private void populateDetailedView(){
		TableLayout tl = (TableLayout) findViewById(R.id.detailedView);
		if (minerStatus instanceof BitpoolStatus){
			render((BitpoolStatus)minerStatus, tl);
		} else if (minerStatus instanceof DeepbitStatus){
			render((DeepbitStatus)minerStatus, tl);
		} else if (minerStatus instanceof SlushStatus){
			render((SlushStatus)minerStatus, tl);
		} else if (minerStatus instanceof BtcMine){
			render((BtcMine)minerStatus, tl);
		} else if (minerStatus instanceof BtcguildStatus){
			render((BtcguildStatus)minerStatus, tl);
		} else if (minerStatus instanceof BitclockersStatus){
			render((BitclockersStatus)minerStatus, tl);
		}  else if (minerStatus instanceof SwepoolStatus){
			render((SwepoolStatus)minerStatus, tl);
		}  else if (minerStatus instanceof MtredStatus){
			render((MtredStatus)minerStatus, tl);
		} else {
			tl.setVisibility(TableLayout.INVISIBLE);
		}
		
	}
	
	private TableRow renderRow(String left, String right){
		TableRow tr = new TableRow(this);
		TextView leftCol = new TextView(getApplicationContext());
		leftCol.setPadding(getDip(5F), getDip(5F), getDip(5F), getDip(5F));
		leftCol.setTextColor(themeService.getTheme().getHeaderTextColor());
		leftCol.setText(left);
		tr.addView(leftCol);
		TextView rightCol = new TextView(getApplicationContext());
		rightCol.setPadding(getDip(10F), getDip(5F), getDip(5F), getDip(5F));
		rightCol.setTextColor(themeService.getTheme().getTextColor());
		rightCol.setText(right);
		tr.addView(rightCol);
		return tr;
	}
	
	private void render(BitpoolStatus status, TableLayout tl){
		tl.addView(renderRow("Username", status.getUsername()));
		tl.addView(renderRow("Status", status.getUser().getStatus()));
		tl.addView(renderRow("Current Speed", status.getUser().getCurrSpeed()));
		tl.addView(renderRow("Curr. Pool Speed", status.getPool().getCurrentSpeed()));
		tl.addView(renderRow("Currrent Round", status.getPool().getCurrentRound()));
		tl.addView(renderRow("Join Date", status.getUser().getJoinDt()));
		tl.addView(renderRow("Last Seen", status.getUser().getLastSeen()));
		tl.addView(renderRow("Active", status.getUser().getActive()));
		tl.addView(renderRow("Estimated Earnings", status.getUser().getEstimated()));
		tl.addView(renderRow("Unconfirmed", status.getUser().getUnconfirmed()));
		tl.addView(renderRow("Historical", status.getUser().getHistorical()));
		tl.addView(renderRow("Unpaid", status.getUser().getUnpaid()));
		StringBuffer sb = new StringBuffer();
		if (status.getUser().getSolvedBlocks() != null){
			for(int i=0;i<status.getUser().getSolvedBlocks().length; i++){
				sb.append(status.getUser().getSolvedBlocks()[i]);
				if(i < status.getUser().getSolvedBlocks().length-1){
					sb.append(',');
				}
			}
		}
		tl.addView(renderRow("Solved Blocks", sb.toString()));
		tl.addView(renderRow("Requested", status.getUser().getRequested().toString()));
		tl.addView(renderRow("Submitted", status.getUser().getSubmitted().toString()));
		tl.addView(renderRow("Efficiency", status.getUser().getEfficiency()));
		tl.addView(renderRow("",""));
	}
	private void render(DeepbitStatus status, TableLayout tl){
		tl.addView(renderRow("Hashrate", status.getHashrate().toString()));
		tl.addView(renderRow("Confirmed Reward", status.getConfirmed_reward().toString()));
		tl.addView(renderRow("Ipa", status.getIpa().toString()));
		tl.addView(renderRow("Worker(s):",""));
		if(status.getWorkers() != null){
		    for( String key : status.getWorkers().keySet() ){
		    	WorkerStatus workerStatus = status.getWorkers().get(key);
		    	tl.addView(renderRow("",key));
		    	tl.addView(renderRow("Alive",workerStatus.getAlive().toString()));
		    	tl.addView(renderRow("Shares",workerStatus.getShares().toString()));
		    	tl.addView(renderRow("Stales",workerStatus.getStales().toString()));
		    	tl.addView(renderRow("",""));
		    }
		}
	}
	
	private void render(BtcguildStatus status, TableLayout tl){
		tl.addView(renderRow("Confirmed Rewards", status.getUser().getConfirmed_rewards().toString()));
		tl.addView(renderRow("Unconfirmed Rewards", status.getUser().getUnconfirmed_rewards().toString()));
		tl.addView(renderRow("Estimated Rewards", status.getUser().getEstimated_rewards().toString()));
		tl.addView(renderRow("Payouts", status.getUser().getPayouts().toString()));
		tl.addView(renderRow("Worker(s):",""));
		if (status.getWorkers() != null){
		    for( String key : status.getWorkers().keySet() ){
		    	BtcguildWorker worker = status.getWorkers().get(key);
		    	tl.addView(renderRow("",worker.getWorker_name()));
		    	tl.addView(renderRow("Hashrate",worker.getHash_rate().toString()));
		    	tl.addView(renderRow("Last Share",worker.getLast_share()));
		    	tl.addView(renderRow("Round Shares",worker.getRound_shares().toString()));
		    	tl.addView(renderRow("Round Stales",worker.getRound_stales().toString()));
		    	tl.addView(renderRow("Total Shares",worker.getTotal_shares().toString()));
		    	tl.addView(renderRow("Total Stales",worker.getTotal_stales().toString()));
		    	tl.addView(renderRow("Blocks Found",worker.getBlocks_found().toString()));
		    	tl.addView(renderRow("",""));
		    }
		}
	}	
	
	private void render(SlushStatus status, TableLayout tl){
		tl.addView(renderRow("Username", status.getUsername()));
		tl.addView(renderRow("Send Threshold", status.getSend_threshold()));
		tl.addView(renderRow("Estimated", status.getEstimated_reward()));
		tl.addView(renderRow("Unconfirmed", status.getUnconfirmed_reward()));
		tl.addView(renderRow("Confirmed", status.getConfirmed_reward()));
		tl.addView(renderRow("Wallet", status.getWallet()));
		if (status.getWorkers() != null){
		    for( String key : status.getWorkers().keySet() ){
		    	SlushWorker worker = status.getWorkers().get(key);
		    	tl.addView(renderRow("",key));
		    	tl.addView(renderRow("Hashrate",worker.getHashrate().toString()));
		    	tl.addView(renderRow("Last Share",worker.getLast_share().toString()));
		    	tl.addView(renderRow("Shares",worker.getShares().toString()));
		    	tl.addView(renderRow("Score",worker.getScore()));
		    	tl.addView(renderRow("Alive",worker.getAlive().toString()));
		    	tl.addView(renderRow("",""));
		    }		
		}		
		tl.addView(renderRow("",""));
	}
	
  
	private void render(BtcMine status, TableLayout tl){
		tl.addView(renderRow("Hashrate", status.getHashrate()));
		tl.addView(renderRow("Total Payout", status.getTotal_payout()));
		tl.addView(renderRow("Total Bounty", status.getTotal_bounty()));
		tl.addView(renderRow("Confirmed Bounty", status.getConfirmed_bounty()));
		tl.addView(renderRow("Estimated Bounty", status.getEstimated_bounty()));
		tl.addView(renderRow("Unconfirmed Bounty", status.getUnconfirmed_bounty()));
		tl.addView(renderRow("Round Shares", status.getRound_shares().toString()));
		tl.addView(renderRow("Solved Shares", status.getSolved_shares().toString()));
		tl.addView(renderRow("Solved Blocks", status.getSolved_blocks().toString()));
		tl.addView(renderRow("",""));
		if (status.getMiners() != null){
		    for( BtcMineWorker worker :  status.getMiners()){
		    	tl.addView(renderRow("",worker.getName()));
		    	tl.addView(renderRow("Online Status",worker.getOnline_status().toString()));
		    	tl.addView(renderRow("Date Connected",worker.getDate_connected()));
		    	tl.addView(renderRow("Solved Shares",worker.getSolved_shares().toString()));
		    	tl.addView(renderRow("Solved Blocks",worker.getSolved_blocks().toString()));
		    }
		}		
	}
	
	private void render(BitclockersStatus status, TableLayout tl){
		tl.addView(renderRow("Balance", status.getBalance().toString()));
		tl.addView(renderRow("Total Withdrawn", status.getPayout()));
		tl.addView(renderRow("Hashrate", status.getHashrate().toString()));
		if (status.getWorkers() != null){
		    for( String key : status.getWorkers().keySet() ){
		    	BitclockersWorker worker = status.getWorkers().get(key);
		    	tl.addView(renderRow("",key));
		    	tl.addView(renderRow("Shares",worker.getShares().toString()));
		    	tl.addView(renderRow("Stale",worker.getStale().toString()));
		    	tl.addView(renderRow("Hashrate",worker.getHashrate()));
		    	tl.addView(renderRow("",""));
		    }
		}
	}
	
	private void render(MtredStatus status, TableLayout tl){
		tl.addView(renderRow("Balance", status.getBalance()));
		tl.addView(renderRow("User Round Solved", status.getRsolved()));
		
		BigDecimal rsolved = new BigDecimal(status.getRsolved());
		BigDecimal roundShares = new BigDecimal(status.getServer().getRoundshares());
		BigDecimal blockValue = new BigDecimal(50);
		BigDecimal estimatedReward = blockValue.multiply(rsolved).divide(roundShares, 4, BigDecimal.ROUND_HALF_UP).setScale(4, BigDecimal.ROUND_HALF_UP);		
		
		tl.addView(renderRow("Estimated Payout", estimatedReward.toString()));
		tl.addView(renderRow("Server Hashrate", status.getServer().getHashrate().toString()));
		tl.addView(renderRow("Server Round Shares", status.getServer().getRoundshares().toString()));
		tl.addView(renderRow("Server Found Block", status.getServer().getFoundblock().toString()));
		tl.addView(renderRow("Server Workers", status.getServer().getWorkers().toString()));
		if (status.getWorkers() != null){
		    for( String key : status.getWorkers().keySet() ){
		    	MtredWorker worker = status.getWorkers().get(key);
		    	tl.addView(renderRow("",key));
		    	tl.addView(renderRow("Round Solved",worker.getRsolved().toString()));
		    	tl.addView(renderRow("Hashrate",worker.getMhash().toString()));
		    	tl.addView(renderRow("",""));
		    }		
		}
	}	
	
	private void render(SwepoolStatus status, TableLayout tl){
		tl.addView(renderRow("Balance", status.getBalance()));
		tl.addView(renderRow("Address", status.getAddress()));
		tl.addView(renderRow("PPS", status.getPaypershare().toString()));
		tl.addView(renderRow("Pool Speed", status.getPool_speed()));
		if (status.getWorkers() != null){
		    for( SwepoolWorker worker : status.getWorkers() ){
		    	tl.addView(renderRow("",worker.getWorker()));
		    	tl.addView(renderRow("Shares",worker.getShares()));
		    	tl.addView(renderRow("Stales",worker.getStales()));
		    	tl.addView(renderRow("Hash Speed",worker.getHashspeed()));
		    	tl.addView(renderRow("",""));
		    }		
		}
	}
	
}