package com.openthinks.bookmarks.client.db;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import com.openthinks.bookmarks.client.Configs;
import com.openthinks.libs.utilities.logger.ProcessLogger;

final class MapDBHelper {

	private static String storeDBPath;
	private static DB dbDisk;
	private static DB dbMemory;
	private static Lock lock = new ReentrantLock();

	static {
		try {
			setUp(Configs.getInstance().get(Configs.PATH_STORE_DB));
		} catch (Exception e) {
			ProcessLogger.error(e);
		}
	}

	/**
	 * set location of DB
	 * 
	 * @param dir
	 *            File director of DB
	 * @param name
	 *            String DB name
	 */
	public static final void setUp(File dir, String name) {
		// File dbFile = new File(WebUtils.getWebClassDir(),
		// StaticDict.STORE_DB);
		File dbFile = new File(dir, name);
		storeDBPath = dbFile.getAbsolutePath();
	}
	
	public static final void setUp(String dbFilePath) {
		storeDBPath = dbFilePath;
	}

	public static final void setUp(File dbFile) {
		storeDBPath = dbFile.getAbsolutePath();
	}

	public static final void initialize() {
		lock.lock();
		try {
			if (dbDisk == null || dbDisk.isClosed()) {
				dbDisk = DBMaker.fileDB(storeDBPath).closeOnJvmShutdown().transactionEnable().make();
			}
			if (dbMemory == null || dbMemory.isClosed()) {
				dbMemory = DBMaker.memoryDB().closeOnJvmShutdown().transactionEnable().make();
			}
		} catch (Exception e) {
			ProcessLogger.fatal(e);
		} finally {
			lock.unlock();
		}
	}

	public static final void destroy() {
		lock.lock();
		try {
			if (dbMemory != null && !dbMemory.isClosed()) {
				dbMemory.close();
			}
			if (dbDisk != null && !dbDisk.isClosed()) {
				dbDisk.close();
			}
		} catch (Exception e) {
			ProcessLogger.fatal(e);
		} finally {
			lock.unlock();
		}
	}

	public static final DB getDiskDB() {
		lock.lock();
		try {
			if (dbDisk == null || dbDisk.isClosed()) {
				dbDisk = DBMaker.fileDB(storeDBPath).closeOnJvmShutdown().transactionEnable().make();
			}
		} catch (Exception e) {
			ProcessLogger.fatal(e);
		} finally {
			lock.unlock();
		}
		return dbDisk;
	}

	public static final DB getMemoryDB() {
		lock.lock();
		try {
			if (dbMemory == null || dbMemory.isClosed()) {
				dbMemory = DBMaker.memoryDB().closeOnJvmShutdown().transactionEnable().make();
			}
		} catch (Exception e) {
			ProcessLogger.fatal(e);
		} finally {
			lock.unlock();
		}
		return dbMemory;
	}

}
