package com.paphus.sdk.activity.actions;

import android.app.Activity;

import com.paphus.sdk.activity.MainActivity;
import com.paphus.sdk.config.ForumPostConfig;

public class HttpUpdateForumPostAction extends HttpUIAction {
	
	ForumPostConfig config;

	public HttpUpdateForumPostAction(Activity activity, ForumPostConfig config) {
		super(activity);
		this.config = config;
	}

	@Override
	protected String doInBackground(Void... params) {
		try {
		this.config = MainActivity.connection.update(this.config);
		} catch (Exception exception) {
			this.exception = exception;
		}
		return "";
	}

	@Override
	public void onPostExecute(String xml) {
		super.onPostExecute(xml);
		if (this.exception != null) {
			return;
		}
		try {
			MainActivity.post = this.config;
			
			this.activity.finish();
			
		} catch (Exception error) {
			this.exception = error;
			MainActivity.error(this.exception.getMessage(), this.exception, this.activity);
			return;
		}
	}

}