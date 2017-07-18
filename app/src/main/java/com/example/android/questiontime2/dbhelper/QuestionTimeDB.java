package com.example.android.questiontime2.dbhelper;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.questiontime2.model.Question;
import com.example.android.questiontime2.model.Quiz;
import com.example.android.questiontime2.model.Type;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for my Database for storing all questions, answers, quizzes, players and scores.
 */

public class QuestionTimeDB extends SQLiteOpenHelper {

    private static final String LOG_TAG = QuestionTimeDB.class.getSimpleName();
    private static final String DATABASE_NAME = "QuestionTime.db";
    private static final int DATABASE_VERSION = 3;

    private Context context;

    // File names of the scripts stored in assets directory
    private static final String CREATE_TABLES = "create_tables.sql";
    private static final String DROP_TABLES = "drop_tables.sql";
    private static final String INSERT_DATA = "insert_data.sql";

    //Declaring all the table names in database
    private static final String QUESTION_TABLE_NAME = "Question";
    private static final String OPTIONS_TABLE_NAME = "Options";
    private static final String QUIZ_TABLE_NAME = "Quiz";
    private static final String TOPIC_TABLE_NAME = "Topic";
    private static final String TYPE_TABLE_NAME = "QuestionType";
    private static final String QUESTION_QUIZ_TABLE_NAME = "QuestionQuiz";

    //Declaring all column names within each table
    private static final String QUESTION_COLUMN_ID = "QuestionID";
    private static final String QUESTION_COLUMN_TEXT = "Question";
    private static final String QUESTION_COLUMN_TOPIC = "TopicID";
    private static final String QUESTION_COLUMN_TYPE = "TypeID";

    private static final String OPTIONS_COLUMN_ID = "OptionsID";
    private static final String OPTIONS_COLUMN_TEXT = "OptionText";
    private static final String OPTIONS_COLUMN_QUESTION = "QuestionID";
    private static final String OPTIONS_COLUMN_IS_CORRECT = "IsCorrect";

    private static final String QUIZ_COLUMN_ID = "QuizID";
    private static final String QUIZ_COLUMN_NAME = "QuizName";

    private static final String TOPIC_COLUMN_ID = "TopicID";
    private static final String TOPIC_COLUMN_NAME = "TopicName";

    private static final String TYPE_COLUMN_ID = "TypeID";
    private static final String TYPE_COLUMN_NAME = "TypeName";
    private static final String TYPE_COLUMN_INSTRUCTION = "TypeInstruction";

    private static final String QUESTION_QUIZ_COLUMN_ID = "QuestionQuizID";
    private static final String QUESTION_QUIZ_COLUMN_QUIZ_ID = "QuizID";
    private static final String QUESTION_QUIZ_COLUMN_QUESTION_ID = "QuestionID";

    //And any useful pre-created views
    private static final String TOP_SCORE_LIST_VIEW = "TopScoresList";

    public QuestionTimeDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        runSQLScript(this.context, db, CREATE_TABLES);
        runSQLScript(this.context, db, INSERT_DATA);
    }

    // On upgrade drop older tables and create new ones calling the onCreate() method.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        runSQLScript(this.context, db, DROP_TABLES);
        onCreate(db);
    }

    /**
     * A method to execute sql scripts. This can be called to run the create_tables.sql script in
     * the onCreate method, along with the insert_data.sql file. n the onUpgrade method, the
     * drop_tables.sql file can be called along with the create and insert scripts. My resource for
     * this method is found below.
     *
     * @param context   The context
     * @param db        The db that the script is run on
     * @param sqlScript The sql script
     * @see <a href="http://www.drdobbs.com/database/using-sqlite-on-android/232900584">Here</a>
     */
    private void runSQLScript(Context context, SQLiteDatabase db, String sqlScript) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte buf[] = new byte[1024];
        int len;
        AssetManager assetManager = context.getAssets();
        InputStream inputStream;

        try {
            inputStream = assetManager.open(sqlScript);
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.close();
            inputStream.close();

            String[] script = outputStream.toString().split(";");
            for (String sqlStatement : script) {
                sqlStatement = sqlStatement.trim();
                if (sqlStatement.length() > 0) {
                    db.execSQL(sqlStatement + ";");
                }
            }
        } catch (IOException e) {
            Log.e(e.toString(), sqlScript + "failed to load");
        } catch (SQLException e) {
            Log.e(e.toString(), sqlScript + "failed to execute");
        }
    }

    public List<Quiz> getAllQuizzes() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT * FROM " + QUIZ_TABLE_NAME + ";";
        Cursor c = db.rawQuery(query, null);
        List<Quiz> quizList = new ArrayList<>();
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                String quizName = c.getString(c.getColumnIndex(QUIZ_COLUMN_NAME));
                int quizID = c.getInt(c.getColumnIndex(QUIZ_COLUMN_ID));

                List<Question> questionList = getQuestionsByQuiz(quizID);

                quizList.add(new Quiz(quizName, questionList));
                c.moveToNext();
            }
            c.close();
            return quizList;
        }
        c.close();
        return null;
    }

    public List<Question> getQuestionsByQuiz(int quizID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT " + QUESTION_TABLE_NAME + "." + QUESTION_COLUMN_TEXT + ", " +
                        QUESTION_TABLE_NAME + "." + QUESTION_COLUMN_ID + ", " +
                        QUESTION_TABLE_NAME + "." + QUESTION_COLUMN_TOPIC + ", " +
                        QUESTION_TABLE_NAME + "." + QUESTION_COLUMN_TYPE + " FROM " +
                        QUESTION_TABLE_NAME + ", " + QUESTION_QUIZ_TABLE_NAME + " WHERE " +
                        QUESTION_QUIZ_COLUMN_QUIZ_ID + " = " + quizID + " AND " +
                        QUESTION_QUIZ_TABLE_NAME + "." + QUESTION_QUIZ_COLUMN_QUESTION_ID + " = " +
                        QUESTION_TABLE_NAME + "." + QUESTION_COLUMN_ID + ";";
        Cursor c = db.rawQuery(query, null);
        List<Question> questionList = new ArrayList<>();
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                String question = c.getString(c.getColumnIndex(QUESTION_COLUMN_TEXT));
                int questionID = c.getInt(c.getColumnIndex(QUESTION_COLUMN_ID));
                int topicID = c.getInt(c.getColumnIndex(QUESTION_COLUMN_TOPIC));
                int typeID = c.getInt(c.getColumnIndex(QUESTION_COLUMN_TYPE));

                List<String> optionsList = getOptionsByQuestion(questionID);
                String topic = getTopic(topicID);
                List<String> answerList = getAnswerByQuestion(questionID);
                Type type = getType(typeID);

                questionList.add(new Question(question, topic, optionsList, answerList, type));
                c.moveToNext();
            }
            c.close();
            return questionList;
        }
        c.close();
        return null;
    }

    public String getTopic(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT * FROM " + TOPIC_TABLE_NAME + " WHERE "
                        + TOPIC_COLUMN_ID + " = " + id + ";";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        String topicName = c.getString(c.getColumnIndex(TOPIC_COLUMN_NAME));
        c.close();
        return topicName;
    }

    public Type getType(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT * FROM " + TYPE_TABLE_NAME + " WHERE "
                        + TYPE_COLUMN_ID + " = " + id + ";";
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
            String typeName = c.getString(c.getColumnIndex(TYPE_COLUMN_NAME));
            String typeInstructions = c.getString(c.getColumnIndex(TYPE_COLUMN_INSTRUCTION));
            c.close();
            return new Type(id, typeName, typeInstructions);
        }
        c.close();
        return null;
    }

    public List<String> getOptionsByQuestion(int questionID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT * FROM " + OPTIONS_TABLE_NAME + " WHERE " +
                        OPTIONS_COLUMN_QUESTION + " = " + questionID + ";";
        List<String> optionsList = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                String optionText = c.getString(c.getColumnIndex(OPTIONS_COLUMN_TEXT));
                optionsList.add(optionText);
                c.moveToNext();
            }
            c.close();
            return optionsList;
        }
        c.close();
        return null;
    }

    public List<String> getAnswerByQuestion(int questionID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
                "SELECT * FROM " + OPTIONS_TABLE_NAME + " WHERE " + OPTIONS_COLUMN_QUESTION +
                        " = " + questionID + " AND " + OPTIONS_COLUMN_IS_CORRECT + " = 1;";
        List<String> answerList = new ArrayList<>();
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            c.moveToFirst();
            for (int i = 0; i < c.getCount(); i++) {
                String optionText = c.getString(c.getColumnIndex(OPTIONS_COLUMN_TEXT));
                answerList.add(optionText);
                c.moveToNext();
            }
            c.close();
            return answerList;
        }
        c.close();
        return null;
    }
}
