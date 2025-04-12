import jp.wasabeef.richeditor.RichEditor

/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 10/04/25
 * Javohir's MacBook Air
 */
class RichEditorHelpIml(private val editor: RichEditor): RichEditorHelp {

    override fun setBold() {
        editor.setBold()
    }

    override fun setItalic() {
        editor.setItalic()
    }

    override fun setUnderline() {
        editor.setUnderline()
    }

    override fun setBulletList() {
        editor.setBullets()
    }

    override fun setNumberedList() {
        editor.setNumbers()
    }

    override fun setCheckBox() {
        editor.insertTodo()
    }

    override fun clearFormats() {
        val currentHtml = editor.html
        val updatedHtml = currentHtml
            .replace("<b>", "").replace("</b>", "")
            .replace("<i>", "").replace("</i>", "")
            .replace("<u>", "").replace("</u>", "")
            .replace("<ul>", "").replace("</ul>", "")
            .replace("<ol>", "").replace("</ol>", "")
            .replace("<li>", "").replace("</li>", "")
            .replace("<input type=\"checkbox\">", "") // Checkbox uchun
        editor.setHtml(updatedHtml)
    }


    override fun getHtml(): String {
        return editor.html
    }

    override fun setHtml(html: String) {
        editor.html = html
    }
}