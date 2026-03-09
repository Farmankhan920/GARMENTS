package com.farman.afgarments.utils

import android.content.Context
import android.os.Environment
import com.farman.afgarments.data.models.Entry
import com.farman.afgarments.data.models.Worker
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfWriter
import com.itextpdf.layout.Document
import com.itextpdf.layout.element.Paragraph
import com.itextpdf.layout.element.Table
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object PdfExporter {

    fun exportHistory(context: Context, entries: List<Entry>): File? {
        try {
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(dir, "AF_Garments_History_${System.currentTimeMillis()}.pdf")

            val writer = PdfWriter(file.absolutePath)
            val pdf = PdfDocument(writer)
            val document = Document(pdf)

            document.add(Paragraph("AF GARMENTS - Production Report").setFontSize(20f).setBold())
            document.add(Paragraph("Generated on: ${FormatUtil.getCurrentDate(Constants.DISPLAY_DATE_FORMAT)}"))
            document.add(Paragraph("\n"))

            val table = Table(floatArrayOf(2f, 2f, 3f, 1f, 1.5f, 1.5f, 1.5f, 1.5f))
            table.addHeaderCell("Date")
            table.addHeaderCell("Worker")
            table.addHeaderCell("Jacket")
            table.addHeaderCell("Qty")
            table.addHeaderCell("Rate")
            table.addHeaderCell("Total")
            table.addHeaderCell("Expense")
            table.addHeaderCell("Net")

            var sumQty = 0
            var sumTotal = 0.0
            var sumExpense = 0.0
            var sumNet = 0.0

            entries.forEach { e ->
                table.addCell(e.date)
                table.addCell(e.workerName)
                table.addCell(e.jacketName)
                table.addCell(e.quantity.toString())
                table.addCell(FormatUtil.formatCurrency(e.price, ""))
                table.addCell(FormatUtil.formatCurrency(e.total, ""))
                table.addCell(FormatUtil.formatCurrency(e.expense, ""))
                table.addCell(FormatUtil.formatCurrency(e.net, ""))

                sumQty += e.quantity
                sumTotal += e.total
                sumExpense += e.expense
                sumNet += e.net
            }

            document.add(table)
            document.add(Paragraph("\nSummary:"))
            document.add(Paragraph("Total Pieces: $sumQty"))
            document.add(Paragraph("Total Earnings: \u20B9${sumTotal}"))
            document.add(Paragraph("Total Expenses: \u20B9${sumExpense}"))
            document.add(Paragraph("Net Income: \u20B9${sumNet}").setBold())

            document.close()
            return file
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }

    fun exportWorkerReport(context: Context, worker: Worker, entries: List<Entry>): File? {
        try {
            val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val file = File(dir, "Worker_${worker.name.replace(" ", "_")}_Report.pdf")

            val writer = PdfWriter(file.absolutePath)
            val pdf = PdfDocument(writer)
            val document = Document(pdf)

            document.add(Paragraph("AF GARMENTS - Worker Report").setFontSize(20f).setBold())
            document.add(Paragraph("Worker: ${worker.name} (${worker.role})").setFontSize(16f))
            document.add(Paragraph("Generated on: ${FormatUtil.getCurrentDate(Constants.DISPLAY_DATE_FORMAT)}"))
            document.add(Paragraph("\n"))

            val table = Table(floatArrayOf(2f, 3f, 1f, 1.5f, 1.5f))
            table.addHeaderCell("Date")
            table.addHeaderCell("Jacket")
            table.addHeaderCell("Qty")
            table.addHeaderCell("Total")
            table.addHeaderCell("Expense")

            var sumQty = 0
            var sumTotal = 0.0
            var sumExpense = 0.0

            entries.forEach { e ->
                table.addCell(e.date)
                table.addCell(e.jacketName)
                table.addCell(e.quantity.toString())
                table.addCell(FormatUtil.formatCurrency(e.total, ""))
                table.addCell(FormatUtil.formatCurrency(e.expense, ""))

                sumQty += e.quantity
                sumTotal += e.total
                sumExpense += e.expense
            }

            document.add(table)
            document.add(Paragraph("\nWorker Summary:"))
            document.add(Paragraph("Total Pieces Done: $sumQty"))
            document.add(Paragraph("Total Amount: \u20B9${sumTotal}"))
            document.add(Paragraph("Expenses / Advances: \u20B9${sumExpense}"))
            document.add(Paragraph("Net Payable: \u20B9${sumTotal - sumExpense}").setBold())

            document.close()
            return file
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}
