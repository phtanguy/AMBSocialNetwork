package eu.telecom_bretagne.ambSocialNetwork.front.android;

//import turpin.mathieu.almanachdumarinbreton.R;

import eu.telecom_bretagne.ambSocialNetwork.R;
import eu.telecom_bretagne.ambSocialNetwork.R.id;
import eu.telecom_bretagne.ambSocialNetwork.R.layout;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginDialog extends Dialog{
		
	public LoginDialog(Context context) {
		super(context);
		
		this.setContentView(R.layout.login);
		this.setCancelable(false);
		this.setTitle("Connexion au serveur");

		final EditText emailaddr = (EditText) findViewById(R.id.email);
		final EditText password = (EditText) findViewById(R.id.password);
		this.show();

		Button login = (Button) findViewById(R.id.boutonLogin);
		login.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String emailText = emailaddr.getText().toString();
				String passwordText = password.getText().toString();
				dismiss();
			}
		});

		Button cancel = (Button) findViewById(R.id.boutonCancel);
		cancel.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v){
				dismiss();
			}
		});
	}
}
