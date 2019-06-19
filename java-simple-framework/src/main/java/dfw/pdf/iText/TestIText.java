package dfw.pdf.iText;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @moudle: TestIText
 * @version:v1.0
 * @author: katlog
 * @date: 2017年9月1日 下午2:39:58
 *
 */
public class TestIText {

	/** 生成一个PDF */
	private static String FILE_DIR = "simpleFramwork/org/person/dfw/pdf/iText/";

	public static void main(String[] args) {
		File f = new File(FILE_DIR);
		if (!f.isDirectory()) {
			f.mkdirs();
		}
		TestIText d = new TestIText();
		try {
			// 生成一个PDF
			d.createSamplePDF();
			// 页面大小,页面背景色,页边空白
			// Title,Author,Subject,Keywords
			d.setDocAttribute();
			// 设置密码
			d.setDocPassword();
			// 添加Page
			d.addPages();
			// 添加水印(背景图)
			d.setWatermark();
			// 插入Chunk, Phrase, Paragraph, List
			d.addContent();
			// 插入Anchor, Image, Chapter, Section
			d.addExtraContent();
			// 画图
			d.draw();
			// 设置段落
			d.setAlignment();
			// 删除Page
			d.deletePage();
			// 插入Page
			d.insertPage();
			// 排序page
			d.sortpage();
			// 目录
			d.addOutline();
			// Header, Footer
			d.setHeaderFooter();
			// 文字左右文字
			d.addColumnText();
			// slideshow
			d.setSlideshow();
			// 压缩PDF到Zip
			d.zipPDF();
			// 分割PDF
			d.splitPDF();
			// 合并PDF
			d.mergePDF();
			// Annotation
			d.setAnnotation();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private void createSamplePDF() throws Exception {
		// Step 1—Create a Document.
		Document document = new Document();
		// Step 2—Get a PdfWriter instance.
		PdfWriter.getInstance(document, new FileOutputStream(FILE_DIR + "createSamplePDF.pdf"));
		// Step 3—Open the Document.
		document.open();
		// Step 4—Add content.
		document.add(new Paragraph("Hello World"));
		// Step 5—Close the Document.
		document.close();
	}

	private void setDocAttribute() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "setDocAttribute.pdf");
		// 页面大小
		Rectangle rect = new Rectangle(PageSize.B5.rotate());
		// 页面背景色
		rect.setBackgroundColor(BaseColor.ORANGE);
		Document doc = new Document(rect);
		PdfWriter writer = PdfWriter.getInstance(doc, out);
		// PDF版本(默认1.4)
		writer.setPdfVersion(PdfWriter.PDF_VERSION_1_2);
		// 文档属性
		doc.addTitle("Title@sample");
		doc.addAuthor("Author@rensanning");
		doc.addSubject("Subject@iText sample");
		doc.addKeywords("Keywords@iText");
		doc.addCreator("Creator@iText");
		// 页边空白
		doc.setMargins(10, 20, 30, 40);
		doc.open();
		doc.add(new Paragraph("Hello World"));
		doc.close();
	}

	private void setDocPassword() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "setDocPassword.pdf");
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, out);
		// 设置密码为："World"
		writer.setEncryption("Hello".getBytes(), "World".getBytes(), PdfWriter.ALLOW_SCREENREADERS,
				PdfWriter.STANDARD_ENCRYPTION_128);
		doc.open();
		doc.add(new Paragraph("Hello World"));
		doc.close();
	}

	private void addPages() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "addPages.pdf");
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		document.open();
		document.add(new Paragraph("First page"));
		document.add(new Paragraph(Document.getVersion()));
		document.newPage();
		writer.setPageEmpty(false);
		document.newPage();
		document.add(new Paragraph("New page"));
		document.close();
	}

	private void setWatermark() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "setWatermark.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		document.add(new Paragraph("First page"));
		document.newPage();
		document.add(new Paragraph("New page"));
		document.newPage();
		document.add(new Paragraph("Third page"));
		document.close();
		// 图片水印
		PdfReader reader = new PdfReader(FILE_DIR + "setWatermark.pdf");
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(FILE_DIR + "setWatermark2.pdf"));
		Image img = Image.getInstance("resource/watermark.jpg");
		img.setAbsolutePosition(200, 400);
		PdfContentByte under = stamp.getUnderContent(1);
		under.addImage(img);
		// 文字水印
		PdfContentByte over = stamp.getOverContent(2);
		over.beginText();
		BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		over.setFontAndSize(bf, 18);
		over.setTextMatrix(30, 30);
		over.showTextAligned(Element.ALIGN_LEFT, "DUPLICATE", 230, 430, 45);
		over.endText();
		// 背景图
		Image img2 = Image.getInstance("resource/test.jpg");
		img2.setAbsolutePosition(0, 0);
		PdfContentByte under2 = stamp.getUnderContent(3);
		under2.addImage(img2);
		stamp.close();
		reader.close();
	}

	private void zipPDF() throws Exception {
		ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(FILE_DIR + "zipPDF.zip"));
		for (int i = 1; i <= 3; i++) {
			ZipEntry entry = new ZipEntry("hello_" + i + ".pdf");
			zip.putNextEntry(entry);
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, zip);
			writer.setCloseStream(false);
			document.open();
			document.add(new Paragraph("Hello " + i));
			document.close();
			zip.closeEntry();
		}
		zip.close();
	}

	private void addContent() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "addContent.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		// Chunk对象: a String, a Font, and some attributes
		document.add(new Chunk("China"));
		document.add(new Chunk(" "));
		Font font = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id = new Chunk("chinese", font);
		id.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id.setTextRise(6);
		document.add(id);
		document.add(Chunk.NEWLINE);
		document.add(new Chunk("Japan"));
		document.add(new Chunk(" "));
		Font font2 = new Font(Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE);
		Chunk id2 = new Chunk("japanese", font2);
		id2.setBackground(BaseColor.BLACK, 1f, 0.5f, 1f, 1.5f);
		id2.setTextRise(6);
		id2.setUnderline(0.2f, -2f);
		document.add(id2);
		document.add(Chunk.NEWLINE);
		// Phrase对象: a List of Chunks with leading
		document.newPage();
		document.add(new Phrase("Phrase page"));
		Phrase director = new Phrase();
		Chunk name = new Chunk("China");
		name.setUnderline(0.2f, -2f);
		director.add(name);
		director.add(new Chunk(","));
		director.add(new Chunk(" "));
		director.add(new Chunk("chinese"));
		director.setLeading(24);
		document.add(director);
		Phrase director2 = new Phrase();
		Chunk name2 = new Chunk("Japan");
		name2.setUnderline(0.2f, -2f);
		director2.add(name2);
		director2.add(new Chunk(","));
		director2.add(new Chunk(" "));
		director2.add(new Chunk("japanese"));
		director2.setLeading(24);
		document.add(director2);
		// Paragraph对象: a Phrase with extra properties and a newline
		document.newPage();
		document.add(new Paragraph("Paragraph page"));
		Paragraph info = new Paragraph();
		info.add(new Chunk("China "));
		info.add(new Chunk("chinese"));
		info.add(Chunk.NEWLINE);
		info.add(new Phrase("Japan "));
		info.add(new Phrase("japanese"));
		document.add(info);
		// List对象: a sequence of Paragraphs called ListItem
		document.newPage();
		List list = new List(List.ORDERED);
		for (int i = 0; i < 10; i++) {
			ListItem item = new ListItem(String.format("%s: %d movies", "country" + (i + 1), (i + 1) * 100), new Font(
					Font.FontFamily.HELVETICA, 6, Font.BOLD, BaseColor.WHITE));
			List movielist = new List(List.ORDERED, List.ALPHABETICAL);
			movielist.setLowercase(List.LOWERCASE);
			for (int j = 0; j < 5; j++) {
				ListItem movieitem = new ListItem("Title" + (j + 1));
				List directorlist = new List(List.UNORDERED);
				for (int k = 0; k < 3; k++) {
					directorlist.add(String.format("%s, %s", "Name1" + (k + 1), "Name2" + (k + 1)));
				}
				movieitem.add(directorlist);
				movielist.add(movieitem);
			}
			item.add(movielist);
			list.add(item);
		}
		document.add(list);
		document.close();
	}

	private void addExtraContent() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "addExtraContent.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		// Anchor对象: internal and external links
		Paragraph country = new Paragraph();
		Anchor dest = new Anchor("china", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
		dest.setName("CN");
		dest.setReference("http://www.china.com");// external
		country.add(dest);
		country.add(String.format(": %d sites", 10000));
		document.add(country);
		document.newPage();
		Anchor toUS = new Anchor("Go to first page.",
				new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE));
		toUS.setReference("#CN");// internal
		document.add(toUS);
		// Image对象
		document.newPage();
		Image img = Image.getInstance("resource/test.jpg");
		img.setAlignment(Image.LEFT | Image.TEXTWRAP);
		img.setBorder(Image.BOX);
		img.setBorderWidth(10);
		img.setBorderColor(BaseColor.WHITE);
		img.scaleToFit(1000, 72);// 大小
		img.setRotationDegrees(-30);// 旋转
		document.add(img);
		// Chapter, Section对象（目录）
		document.newPage();
		Paragraph title = new Paragraph("Title");
		Chapter chapter = new Chapter(title, 1);
		title = new Paragraph("Section A");
		Section section = chapter.addSection(title);
		section.setBookmarkTitle("bmk");
		section.setIndentation(30);
		section.setBookmarkOpen(false);
		section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
		Section subsection = section.addSection(new Paragraph("Sub Section A"));
		subsection.setIndentationLeft(20);
		subsection.setNumberDepth(1);
		document.add(chapter);
		document.close();
	}

	private void draw() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "draw.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		// 左右箭头
		document.add(new VerticalPositionMark() {

			public void draw(PdfContentByte canvas, float llx, float lly, float urx, float ury, float y) {
				canvas.beginText();
				BaseFont bf = null;
				try {
					bf = BaseFont.createFont(BaseFont.ZAPFDINGBATS, "", BaseFont.EMBEDDED);
				} catch (Exception e) {
					e.printStackTrace();
				}
				canvas.setFontAndSize(bf, 12);
				// LEFT
				canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), llx - 10, y, 0);
				// RIGHT
				canvas.showTextAligned(Element.ALIGN_CENTER, String.valueOf((char) 220), urx + 10, y + 8, 180);
				canvas.endText();
			}
		});
		// 直线
		Paragraph p1 = new Paragraph("LEFT");
		p1.add(new Chunk(new LineSeparator()));
		p1.add("R");
		document.add(p1);
		// 点线
		Paragraph p2 = new Paragraph("LEFT");
		p2.add(new Chunk(new DottedLineSeparator()));
		p2.add("R");
		document.add(p2);
		// 下滑线
		LineSeparator UNDERLINE = new LineSeparator(1, 100, null, Element.ALIGN_CENTER, -2);
		Paragraph p3 = new Paragraph("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
		p3.add(UNDERLINE);
		document.add(p3);
		document.close();
	}

	private void setAlignment() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "setAlignment.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		Paragraph p = new Paragraph(
				"In the previous example, you added a header and footer with the showTextAligned() _03method. This example demonstrates that it’s sometimes more interesting to use PdfPTable and writeSelectedRows(). You can define a bottom border for each cell so that the header is underlined. This is the most elegant way to add headers and footers, because the table mechanism allows you to position and align lines, images, and text.");
		// 默认
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		document.add(p);
		// 徐々に右のほうに移動します。
		document.newPage();
		p.setAlignment(Element.ALIGN_JUSTIFIED);
		p.setIndentationLeft(1 * 15f);
		p.setIndentationRight((5 - 1) * 15f);
		document.add(p);
		// 居右
		document.newPage();
		p.setAlignment(Element.ALIGN_RIGHT);
		p.setSpacingAfter(15f);
		document.add(p);
		// 居左
		document.newPage();
		p.setAlignment(Element.ALIGN_LEFT);
		p.setSpacingBefore(15f);
		document.add(p);
		// 居中
		document.newPage();
		p.setAlignment(Element.ALIGN_CENTER);
		p.setSpacingAfter(15f);
		p.setSpacingBefore(15f);
		document.add(p);
		document.close();
	}

	private void deletePage() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "deletePage.pdf");
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		document.open();
		document.add(new Paragraph("First page"));
		document.add(new Paragraph(Document.getVersion()));
		document.newPage();
		writer.setPageEmpty(false);
		document.newPage();
		document.add(new Paragraph("New page"));
		document.close();
		PdfReader reader = new PdfReader(FILE_DIR + "deletePage.pdf");
		reader.selectPages("1,3");
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(FILE_DIR + "deletePage2.pdf"));
		stamp.close();
		reader.close();
	}

	private void insertPage() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "insertPage.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		document.add(new Paragraph("1 page"));
		document.newPage();
		document.add(new Paragraph("2 page"));
		document.newPage();
		document.add(new Paragraph("3 page"));
		document.close();
		PdfReader reader = new PdfReader(FILE_DIR + "insertPage.pdf");
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(FILE_DIR + "insertPage2.pdf"));
		stamp.insertPage(2, reader.getPageSize(1));
		ColumnText ct = new ColumnText(null);
		ct.addElement(new Paragraph(24, new Chunk("INSERT PAGE")));
		ct.setCanvas(stamp.getOverContent(2));
		ct.setSimpleColumn(36, 36, 559, 770);
		stamp.close();
		reader.close();
	}

	private void splitPDF() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "splitPDF.pdf");
		Document document = new Document();
		PdfWriter.getInstance(document, out);
		document.open();
		document.add(new Paragraph("1 page"));
		document.newPage();
		document.add(new Paragraph("2 page"));
		document.newPage();
		document.add(new Paragraph("3 page"));
		document.newPage();
		document.add(new Paragraph("4 page"));
		document.close();
		PdfReader reader = new PdfReader(FILE_DIR + "splitPDF.pdf");
		Document dd = new Document();
		PdfWriter writer = PdfWriter.getInstance(dd, new FileOutputStream(FILE_DIR + "splitPDF1.pdf"));
		dd.open();
		PdfContentByte cb = writer.getDirectContent();
		dd.newPage();
		cb.addTemplate(writer.getImportedPage(reader, 1), 0, 0);
		dd.newPage();
		cb.addTemplate(writer.getImportedPage(reader, 2), 0, 0);
		dd.close();
		writer.close();
		Document dd2 = new Document();
		PdfWriter writer2 = PdfWriter.getInstance(dd2, new FileOutputStream(FILE_DIR + "splitPDF2.pdf"));
		dd2.open();
		PdfContentByte cb2 = writer2.getDirectContent();
		dd2.newPage();
		cb2.addTemplate(writer2.getImportedPage(reader, 3), 0, 0);
		dd2.newPage();
		cb2.addTemplate(writer2.getImportedPage(reader, 4), 0, 0);
		dd2.close();
		writer2.close();
	}

	private void mergePDF() throws Exception {
		PdfReader reader1 = new PdfReader(FILE_DIR + "splitPDF1.pdf");
		PdfReader reader2 = new PdfReader(FILE_DIR + "splitPDF2.pdf");
		FileOutputStream out = new FileOutputStream(FILE_DIR + "mergePDF.pdf");
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		document.open();
		PdfContentByte cb = writer.getDirectContent();
		int totalPages = 0;
		totalPages += reader1.getNumberOfPages();
		totalPages += reader2.getNumberOfPages();
		java.util.List<PdfReader> readers = new ArrayList<PdfReader>();
		readers.add(reader1);
		readers.add(reader2);
		int pageOfCurrentReaderPDF = 0;
		Iterator<PdfReader> iteratorPDFReader = readers.iterator();
		// Loop through the PDF files and add to the output.
		while (iteratorPDFReader.hasNext()) {
			PdfReader pdfReader = iteratorPDFReader.next();
			// Create a new page in the target for each processor page.
			while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
				document.newPage();
				pageOfCurrentReaderPDF++;
				PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
				cb.addTemplate(page, 0, 0);
			}
			pageOfCurrentReaderPDF = 0;
		}
		out.flush();
		document.close();
		out.close();
	}

	private void addOutline() throws Exception {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FILE_DIR + "addOutline.pdf"));
		document.open();
		// Code 1
		document.add(new Chunk("Chapter 1").setLocalDestination("1"));
		document.newPage();
		document.add(new Chunk("Chapter 2").setLocalDestination("2"));
		document.add(new Paragraph(new Chunk("Sub 2.1").setLocalDestination("2.1")));
		document.add(new Paragraph(new Chunk("Sub 2.2").setLocalDestination("2.2")));
		document.newPage();
		document.add(new Chunk("Chapter 3").setLocalDestination("3"));
		// Code 2
		PdfContentByte cb = writer.getDirectContent();
		PdfOutline root = cb.getRootOutline();
		// Code 3
		@SuppressWarnings("unused")
		PdfOutline oline1 = new PdfOutline(root, PdfAction.gotoLocalPage("1", false), "Chapter 1");
		PdfOutline oline2 = new PdfOutline(root, PdfAction.gotoLocalPage("2", false), "Chapter 2");
		oline2.setOpen(false);
		@SuppressWarnings("unused")
		PdfOutline oline2_1 = new PdfOutline(oline2, PdfAction.gotoLocalPage("2.1", false), "Sub 2.1");
		@SuppressWarnings("unused")
		PdfOutline oline2_2 = new PdfOutline(oline2, PdfAction.gotoLocalPage("2.2", false), "Sub 2.2");
		@SuppressWarnings("unused")
		PdfOutline oline3 = new PdfOutline(root, PdfAction.gotoLocalPage("3", false), "Chapter 3");
		document.close();
	}

	private void setHeaderFooter() throws Exception {
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(FILE_DIR + "setHeaderFooter.pdf"));
		writer.setPageEvent(new PdfPageEventHelper() {

			public void onEndPage(PdfWriter writer, Document document) {
				PdfContentByte cb = writer.getDirectContent();
				cb.saveState();
				cb.beginText();
				BaseFont bf = null;
				try {
					bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
				} catch (Exception e) {
					e.printStackTrace();
				}
				cb.setFontAndSize(bf, 10);
				// Header
				float x = document.top(-20);
				// 左
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "H-Left", document.left(), x, 0);
				// 中
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, writer.getPageNumber() + " page",
						(document.right() + document.left()) / 2, x, 0);
				// 右
				cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "H-Right", document.right(), x, 0);
				// Footer
				float y = document.bottom(-20);
				// 左
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "F-Left", document.left(), y, 0);
				// 中
				cb.showTextAligned(PdfContentByte.ALIGN_CENTER, writer.getPageNumber() + " page",
						(document.right() + document.left()) / 2, y, 0);
				// 右
				cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "F-Right", document.right(), y, 0);
				cb.endText();
				cb.restoreState();
			}
		});
		doc.open();
		doc.add(new Paragraph("1 page"));
		doc.newPage();
		doc.add(new Paragraph("2 page"));
		doc.newPage();
		doc.add(new Paragraph("3 page"));
		doc.newPage();
		doc.add(new Paragraph("4 page"));
		doc.close();
	}

	private void addColumnText() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "addColumnText.pdf");
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		document.open();
		PdfContentByte canvas = writer.getDirectContent();
		Phrase phrase1 = new Phrase("This is a test!left");
		Phrase phrase2 = new Phrase("This is a test!right");
		Phrase phrase3 = new Phrase("This is a test!center");
		ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, phrase1, 10, 500, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_RIGHT, phrase2, 10, 536, 0);
		ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, phrase3, 10, 572, 0);
		document.close();
	}

	private void setSlideshow() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "setSlideshow.pdf");
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, out);
		writer.setPdfVersion(PdfWriter.VERSION_1_5);
		writer.setViewerPreferences(PdfWriter.PageModeFullScreen);// 全屏
		writer.setPageEvent(new PdfPageEventHelper() {

			public void onStartPage(PdfWriter writer, Document document) {
				writer.setTransition(new PdfTransition(PdfTransition.DISSOLVE, 3));
				writer.setDuration(5);// 间隔时间
			}
		});
		doc.open();
		doc.add(new Paragraph("1 page"));
		doc.newPage();
		doc.add(new Paragraph("2 page"));
		doc.newPage();
		doc.add(new Paragraph("3 page"));
		doc.newPage();
		doc.add(new Paragraph("4 page"));
		doc.newPage();
		doc.add(new Paragraph("5 page"));
		doc.close();
	}

	private void sortpage() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "sortpage.pdf");
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, out);
		writer.setLinearPageMode();
		doc.open();
		doc.add(new Paragraph("1 page"));
		doc.newPage();
		doc.add(new Paragraph("2 page"));
		doc.newPage();
		doc.add(new Paragraph("3 page"));
		doc.newPage();
		doc.add(new Paragraph("4 page"));
		doc.newPage();
		doc.add(new Paragraph("5 page"));
		int[] order = { 4, 3, 2, 1 };
		writer.reorderPages(order);
		doc.close();
	}

	private void setAnnotation() throws Exception {
		FileOutputStream out = new FileOutputStream(FILE_DIR + "setAnnotation.pdf");
		Document doc = new Document();
		PdfWriter writer = PdfWriter.getInstance(doc, out);
		writer.setLinearPageMode();
		doc.open();
		doc.add(new Paragraph("1 page"));
		doc.add(new Annotation("Title", "This is a annotation!"));
		doc.newPage();
		doc.add(new Paragraph("2 page"));
		Chunk chunk = new Chunk("\u00a0");
		chunk.setAnnotation(PdfAnnotation.createText(writer, null, "Title", "This is a another annotation!", false,
				"Comment"));
		doc.add(chunk);
		// 添加附件
		doc.newPage();
		doc.add(new Paragraph("3 page"));
		Chunk chunk2 = new Chunk("\u00a0\u00a0");
		PdfAnnotation annotation = PdfAnnotation.createFileAttachment(writer, null, "Title", null,
				"resource/test2.jpg", "img.jpg");
		annotation.put(PdfName.NAME, new PdfString("Paperclip"));
		chunk2.setAnnotation(annotation);
		doc.add(chunk2);
		doc.close();
	}
}
