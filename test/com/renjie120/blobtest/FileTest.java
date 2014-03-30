package com.renjie120.blobtest;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;

import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.lob.SerializableBlob;
import org.springframework.test.AbstractTransactionalDataSourceSpringContextTests;

public class FileTest extends AbstractTransactionalDataSourceSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		String[] config = new String[] { "test/spring.cfg.xml" };
		return config;
	}

	public void testSaveFile() {
		Session s = HibernateSessionFactory.getSession();
		Transaction tx = s.beginTransaction();
		byte[] buffer = new byte[1];
		buffer[0] = 1;
		Blobtest blobtest = new Blobtest();
		blobtest.setBlobId(1);
		blobtest.setBlobName("jpg");
		blobtest.setBlobContent(Hibernate.createBlob(buffer));
		// 先保存一个空的BLOB对象
		s.save(blobtest);
		s.flush();
		// 获得blob的cursor
		s.refresh(blobtest, LockMode.UPGRADE);
		SerializableBlob blob1 = (SerializableBlob) blobtest.getBlobContent();
		Blob wrappedBlob = blob1.getWrappedBlob();
		File f = new File("d:\\1.jpg");
		FileInputStream fin = new FileInputStream(f);
		int count = -1;
		int total = 0;
		byte[] data = new byte[(int) fin.available()];
		fin.read(data);
		wrappedBlob.setBytes(1, data);
		blobtest.setBlobContent(wrappedBlob);
		s.flush();
		tx.commit();
	}
}
