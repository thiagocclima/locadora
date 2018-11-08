package com.thiago.locadora.report;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JasperReport {
	
	String DEFAULT_PATH = "src/main/resources/reports/";
	
	JasperPrint jasperPrint = null;
	
	public JasperReport(String nomeRelatorioJasper, Collection<?> collection, HttpServletResponse response) throws JRException, IOException {
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(collection);
		
		String path = new File(DEFAULT_PATH + nomeRelatorioJasper).getAbsolutePath();
		
		jasperPrint = JasperFillManager.fillReport(path, null, dataSource);
		
	}

	public void exportToPDF(HttpServletResponse response) throws JRException, IOException {
		byte[] pdfData = JasperExportManager.exportReportToPdf(jasperPrint);

		response.reset(); 
	    response.setContentType("application/pdf"); 
	    response.setHeader("Content-disposition", "inline; filename=\"name.pdf\""); 

	    OutputStream output = response.getOutputStream();
	    output.write(pdfData);
	    output.close();
	}
	
	

}
