DataScienceService - Classify and Extract Bank Transactions

Overview
The TransactionClassifierService uses Mitral AI's Large Language Model to analyze and classify bank transaction messages. By leveraging Mitral AI’s API, the service identifies whether a transaction is a credit or debit, and extracts essential details such as:

Transaction Type (Credit or Debit)

Amount

Merchant Name (in case of credit)

This service is designed to help automatically categorize transactions from bank messages and store or use the extracted information for further processing.

Key Features
Transaction Classification: Classifies bank transaction messages into Credit or Debit.

Amount Extraction: Extracts the amount involved in the transaction.

Merchant Identification: For credit transactions, it identifies the merchant to whom the money has been credited.

API Integration: Utilizes Mitral AI’s Large Language Model API to process and analyze transaction data.

How It Works
The service sends a bank message (text) to the Mitral AI model via its API.

The model processes the text, classifies the transaction type (credit or debit), and extracts relevant details such as:

Transaction Type (Credit/Debit)

Amount

Merchant Name (in case of credit)
