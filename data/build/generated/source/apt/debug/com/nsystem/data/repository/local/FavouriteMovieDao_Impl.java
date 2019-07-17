package com.nsystem.data.repository.local;

import android.database.Cursor;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.RxRoom;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.nsystem.data.entity.FavouriteEntity;
import io.reactivex.Observable;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FavouriteMovieDao_Impl implements FavouriteMovieDao {
  private final RoomDatabase __db;

  public FavouriteMovieDao_Impl(RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Observable<List<FavouriteEntity>> getFavouriteMovieList() {
    final String _sql = "SELECT * FROM Favourite";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return RxRoom.createObservable(__db, false, new String[]{"Favourite"}, new Callable<List<FavouriteEntity>>() {
      @Override
      public List<FavouriteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false);
        try {
          final int _cursorIndexOfMovieId = CursorUtil.getColumnIndexOrThrow(_cursor, "movieId");
          final int _cursorIndexOfPosterPath = CursorUtil.getColumnIndexOrThrow(_cursor, "posterPath");
          final List<FavouriteEntity> _result = new ArrayList<FavouriteEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final FavouriteEntity _item;
            _item = new FavouriteEntity();
            final int _tmpMovieId;
            _tmpMovieId = _cursor.getInt(_cursorIndexOfMovieId);
            _item.setMovieId(_tmpMovieId);
            final String _tmpPosterPath;
            _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
            _item.setPosterPath(_tmpPosterPath);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
