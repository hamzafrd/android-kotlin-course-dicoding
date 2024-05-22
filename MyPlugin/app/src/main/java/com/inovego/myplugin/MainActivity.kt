package com.inovego.myplugin


class CatAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val selectedText = e.getData(PlatformDataKeys.EDITOR)?.selectionModel?.selectedText ?: ""
        val className = e.getData(PlatformDataKeys.PSI_ELEMENT)?.let { it.containingFile.nameWithoutExtension }
        val message = selectedText.ifEmpty { "No message provided" }
        val logger = Logger.getInstance(CatAction::class.java)
        logger.info("CatLog $className: $message")
    }
}
