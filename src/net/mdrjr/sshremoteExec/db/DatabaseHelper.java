package net.mdrjr.sshremoteExec.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	// Define database version, in case we want to upgrade it later
	private static final int VERSAO = 1;
	// Database File name
	private static final String SSHREMOTEEXECdb = "sshredb.db";
	
	
	public DatabaseHelper(Context context) {
		super(context, SSHREMOTEEXECdb, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase sqliteDataBase, ConnectionSource connectionSource) {
		try {
			Log.e("BD", "Created");
			// Create our tables if they don't exist :) 
			TableUtils.createTable(connectionSource, Server.class);
			TableUtils.createTable(connectionSource, Command.class);
		} catch (SQLException e) {
			Log.e("BD", e.getMessage());
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqliteDataBase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			// on Upgrade drop and create again.
			TableUtils.dropTable(connectionSource, Server.class,  true);
			TableUtils.dropTable(connectionSource, Command.class, true);
			onCreate(sqliteDataBase, connectionSource);
		} catch (SQLException e) {
			Log.e("BD", e.getMessage());
		}
		
	}

}
