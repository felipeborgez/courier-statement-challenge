INSERT INTO delivery (id, courier_id) values
('45be397e-c53d-4376-8b57-7ca5a5fc46cb', 'a1738301-69e2-42cb-9cd4-3054759802bf'),
('57a79bdc-ae30-4033-883c-48a134ec6624', '5c4a1a39-e3b9-4d85-a449-0b7f3411e656'),
('4ae40385-dfd0-402e-8c7a-e0defb799c4b', '00d3b8ab-45b6-4cf6-8bcc-078570fd6981'),
('6be35b2e-0530-4946-ae65-c7b97775cd8e', '4e9c512e-c9c8-4734-8747-ee5755507128');

INSERT INTO "transaction" (id,delivery_id,"type",transaction_reference_id,occurred_at,value,metadata,inserted_at) VALUES
 ('e8675429-f783-4197-a634-3f90123ba382','45be397e-c53d-4376-8b57-7ca5a5fc46cb','CREATION','39124d70-872f-4516-8235-aaca10cd65ce','2023-12-28 20:12:51.210',5600,NULL,'2023-12-28 20:12:51.210'),
 ('ae3be707-6815-44c1-b7f0-d0f87af8d93d','45be397e-c53d-4376-8b57-7ca5a5fc46cb','ADJUSTMENT','0f267ba4-928b-4d91-ae49-4b373d72e438','2023-12-28 20:13:51.090',5600,NULL,'2023-12-28 20:13:51.090'),
 ('59d7958f-8f81-4347-b38e-58eec53f8630','45be397e-c53d-4376-8b57-7ca5a5fc46cb','BONUS','c7a84979-0e44-4c3d-821d-6d87a0bbc4cd','2023-12-28 20:13:53.138',5600,NULL,'2023-12-28 20:13:53.138'),
 ('3838db7d-2566-43f5-96c0-f2c500e6738e','57a79bdc-ae30-4033-883c-48a134ec6624','CREATION','322be9c0-40f2-4829-912f-9db3bbb210ea','2023-12-28 20:14:53.273',800,NULL,'2023-12-28 20:14:53.273'),
 ('501c581a-5a16-47a5-b69e-9a1e32b80974','57a79bdc-ae30-4033-883c-48a134ec6624','ADJUSTMENT','34dfa1df-e128-4a88-911c-35354a299fde','2023-12-28 20:14:54.956',500,NULL,'2023-12-28 20:14:54.956'),
 ('710af2d4-90cd-4868-b67a-d7d96fd732ef','57a79bdc-ae30-4033-883c-48a134ec6624','BONUS','c21788d7-6e3f-41b9-831c-a15f98b076ca','2023-12-28 20:14:56.255',500,NULL,'2023-12-28 20:14:56.255'),
 ('917be818-8946-4b6c-9216-a50693be37ca','4ae40385-dfd0-402e-8c7a-e0defb799c4b','CREATION','648ca7a2-9883-4728-af70-2e148a6f950f','2023-12-28 20:15:11.420',800,NULL,'2023-12-28 20:15:11.420'),
 ('7983f3cb-edd3-487c-809d-6e58b54ff1e3','4ae40385-dfd0-402e-8c7a-e0defb799c4b','ADJUSTMENT','ade0b8ae-742a-4b33-a2f1-cf2ce3ffc703','2023-12-28 20:15:21.705',500,NULL,'2023-12-28 20:15:21.705'),
 ('29f7bf3c-15b6-4766-a835-c9341f1d05eb','6be35b2e-0530-4946-ae65-c7b97775cd8e','BONUS','2e4f661f-83e8-4729-ba58-57d6af6858a2','2023-12-28 20:16:48.049',500,NULL,'2023-12-28 20:16:48.049');