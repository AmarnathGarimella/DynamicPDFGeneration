# DynamicPDFGeneration

**Features**
---
1. Generate the invoice and store it locally in `pdf-storage` folder.
2. If the current request data is same as any previous request data, then old file will be sent.
3. Can Download an invoice by giving the filename. 

**Assumptions**
---
1. The Request Schema is modeled after the example request data.
2. Any field of the request data is not null.
3. If any two request's list items are same but their order is different then the two requests data is considered as different.

