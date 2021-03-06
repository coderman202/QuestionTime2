CREATE TABLE IF NOT EXISTS Topic ( 
TopicID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 
TopicName TEXT NOT NULL );

CREATE TABLE IF NOT EXISTS QuestionType(
TypeID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
TypeName TEXT NOT NULL,
TypeInstruction TEXT NOT NULL);

CREATE TABLE IF NOT EXISTS Question ( 
QuestionID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 
Question TEXT NOT NULL, 
TopicID INTEGER NOT NULL,  
TypeID INTEGER, 
FOREIGN KEY(TopicID) REFERENCES Topic(TopicID) ON DELETE SET NULL, 
FOREIGN KEY(TypeID) REFERENCES QuestionType(TypeID) ON DELETE SET NULL );

CREATE TABLE IF NOT EXISTS Options ( 
OptionID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 
QuestionID INTEGER NOT NULL, 
OptionText TEXT NOT NULL, 
IsCorrect INTEGER NOT NULL, 
FOREIGN KEY(QuestionID) REFERENCES Question(QuestionID) ON DELETE SET NULL );

CREATE TABLE IF NOT EXISTS Quiz ( 
QuizID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,  
QuizName TEXT NOT NULL);

CREATE TABLE IF NOT EXISTS QuestionQuiz ( 
QuestionQuiz INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, 
QuestionID INTEGER NOT NULL, 
QuizID INTEGER NOT NULL, 
FOREIGN KEY(QuestionID) REFERENCES Question(QuestionID) ON DELETE SET NULL, 
FOREIGN KEY(QuizID) REFERENCES Quiz(QuizID) ON DELETE SET NULL );