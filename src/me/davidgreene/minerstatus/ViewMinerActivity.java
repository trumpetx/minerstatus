package me.davidgreene.minerstatus;

import me.davidgreene.minerstatus.beans.Renderable;
import me.davidgreene.minerstatus.beans.Status;
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
		
		if (minerStatus instanceof Renderable){
			Renderable renderableMinerStatus = (Renderable) minerStatus;
			renderableMinerStatus.render(this);
		} else {
			TableLayout tl = (TableLayout) findViewById(R.id.detailedView);
			tl.setVisibility(TableLayout.INVISIBLE);
		}
		
	}
	
	public TableRow renderRow(String left, Object right){
		TableRow tr = new TableRow(this);
		TextView leftCol = new TextView(getApplicationContext());
		leftCol.setPadding(getDip(5F), getDip(5F), getDip(5F), getDip(5F));
		leftCol.setTextColor(themeService.getTheme().getHeaderTextColor());
		leftCol.setText(left);
		tr.addView(leftCol);
		TextView rightCol = new TextView(getApplicationContext());
		rightCol.setPadding(getDip(10F), getDip(5F), getDip(5F), getDip(5F));
		rightCol.setTextColor(themeService.getTheme().getTextColor());
		rightCol.setText(right.toString());
		tr.addView(rightCol);
		return tr;
	}	
	
}
