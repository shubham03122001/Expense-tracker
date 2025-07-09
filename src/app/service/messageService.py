from app.utils.messageUtil import messageUtil  # ✅ Correct

from .llmService import LLMService


class MessageService:
    def __init__(self):
        self.messageUtil = messageUtil()
        self.llmService = LLMService()


    def process_message(self , message):
        if self.messageUtil.isBankSMS(message):
            expense=self.llmService.runLLM(message)
            return expense.dict()
            # return self.llmService.runLLM(message)
        
        else:
            return None