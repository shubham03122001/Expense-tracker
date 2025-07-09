from pydantic import BaseModel, Field


from typing import Optional

class Expense(BaseModel):
    amount : Optional[str]= Field(title="expense",description="Expense made in transaction")
    merchant : Optional[str] = Field(title="merchant",description="Merchant name to whom we have made the transaction")
    currency : Optional[str] =Field(title="currency",description="Currency of the transaction")
    