/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package com.sleepycat.db.internal;

import com.sleepycat.db.*;
import java.util.Comparator;

public class DbSite {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected DbSite(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(DbSite obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  /* package */ synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        throw new UnsupportedOperationException("C++ destructor does not have public access");
      }
      swigCPtr = 0;
    }
  }

        public ReplicationManagerSite wrapper;
	public synchronized void close() throws DatabaseException {
		try {
			close0();
		} finally {
			swigCPtr = 0;
		}
	}

	public synchronized void remove() throws DatabaseException {
		try {
			remove0();
		} finally {
			swigCPtr = 0;
		}
	}

  /* package */ void close0() { db_javaJNI.DbSite_close0(swigCPtr, this); }

  public com.sleepycat.db.ReplicationHostAddress get_address() throws com.sleepycat.db.DatabaseException { return db_javaJNI.DbSite_get_address(swigCPtr, this); }

  public boolean get_config(int which) throws com.sleepycat.db.DatabaseException { return db_javaJNI.DbSite_get_config(swigCPtr, this, which); }

  public int get_eid() throws com.sleepycat.db.DatabaseException {
    return db_javaJNI.DbSite_get_eid(swigCPtr, this);
  }

  /* package */ void remove0() { db_javaJNI.DbSite_remove0(swigCPtr, this); }

  public void set_config(int which, boolean onoff) throws com.sleepycat.db.DatabaseException { db_javaJNI.DbSite_set_config(swigCPtr, this, which, onoff); }

}
