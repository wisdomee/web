
DROP TABLE userBean cascade constraint;

CREATE TABLE USERBEAN (
       id VARCHAR2(200)  PRIMARY KEY,
       pw VARCHAR2(200) NOT NULL,
       nickName VARCHAR2(200) NOT NULL,
       lastAccessTime NUMBER(30) NOT NULL
);

commit;