/**
 * Creator: Javohir Oromov
 * Project: Eslatma
 * Date: 10/04/25
 * Javohir's MacBook Air
 */
interface RichEditorHelp {
    fun setBold()
    fun setItalic()
    fun setUnderline()
    fun setBulletList()
    fun setNumberedList()
    fun setCheckBox()
    fun clearFormats()
    fun getHtml(): String?
    fun setHtml(html: String)

}