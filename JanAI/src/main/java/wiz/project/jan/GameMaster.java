/**
 * GameMaster.java
 */

package wiz.project.jan;

import java.io.IOException;
import java.util.List;

import wiz.project.jan.exception.AlreadyStartedException;
import wiz.project.jan.exception.JanException;
import wiz.project.jan.exception.NotStartedException;



/**
 * ゲーム管理者
 */
public final class GameMaster {
    
    /**
     * コンストラクタを自分自身に限定許可
     */
    private GameMaster() {
    }
    
    
    
    /**
     * インスタンスを取得
     * 
     * @return インスタンス
     */
    public static GameMaster getInstance() {
        return INSTANCE;
    }
    
    
    
    /**
     * 初期化処理
     * 
     * @param announcer 実況者。
     */
    public void initialize(final AnnounceThread announcer) {
        if (announcer != null) {
            synchronized (_INFO_LOCK) {
                final EventThread e = new EventThread(_core);
                e.run();
                _info.addObserver(e);
                _info.addObserver(announcer);
            }
        }
    }
    
    /**
     * 鳴き可能時の処理
     * 
     * @throws NotStartedException ゲーム未開始。
     * @throws JanException 麻雀ゲームの例外。
     */
    public void onCallable() throws NotStartedException, JanException {
        synchronized (_INFO_LOCK) {
            switch (_info.getState()) {
            case CONFIRMING:
                break;
            case CLOSE:
                throw new NotStartedException();
            default:
                throw new IllegalStateException("Invalid game state.");
            }
            
            _info.setState(GameState.PROCESSING);
        }
    }
    
    /**
     * ゲーム終了時の処理
     * 
     * @throws JanException 麻雀ゲームの例外。
     */
    public void onEnd() throws JanException {
        synchronized (_INFO_LOCK) {
            switch (_info.getState()) {
            case CLOSE:
                // 何もしない
                return;
            default:
                // あらゆる状態から強制終了を可能とする
                break;
            }
            
            _info.clear();
            _info.setState(GameState.CLOSE);
        }
    }
    
    /**
     * プレイヤーターンの処理
     * 
     * @throws NotStartedException ゲーム未開始。
     * @throws JanException 麻雀ゲームの例外。
     */
    public void onPlayerTurn() throws NotStartedException, JanException {
        synchronized (_INFO_LOCK) {
            switch (_info.getState()) {
            case IDLE:
                break;
            case CLOSE:
                throw new NotStartedException();
            default:
                throw new IllegalStateException("Invalid game state.");
            }
            
            _info.setState(GameState.PROCESSING);
        }
    }
    
    /**
     * ゲームリプレイ時の処理
     * 
     * @throws AlreadyStartedException ゲーム開始済み。
     * @throws JanException 麻雀ゲームの例外。
     * @throws IOException ファイル読み込みに失敗。
     */
    public void onReplay() throws AlreadyStartedException, JanException, IOException {
        synchronized (_INFO_LOCK) {
            switch (_info.getState()) {
            case CLOSE:
                break;
            default:
                throw new AlreadyStartedException();
            }
            
            final PermanenceController permanence = createPermanenceController();
            _info = permanence.restore(GAME_SAVE_PATH);
            
            synchronized (_CORE_LOCK) {
                _core.start(_info);
            }
        }
    }
    
    /**
     * ゲーム開始時の処理
     * 
     * @param playerNameList プレイヤー名のリスト。
     * @throws AlreadyStartedException ゲーム開始済み。
     * @throws JanException 麻雀ゲームの例外。
     */
    public void onStart(final List<String> playerNameList) throws AlreadyStartedException, JanException {
        synchronized (_INFO_LOCK) {
            switch (_info.getState()) {
            case CLOSE:
                break;
            default:
                throw new AlreadyStartedException();
            }
            
            synchronized (_CORE_LOCK) {
                _core.standBy(_info, playerNameList);
                
                try {
                    final PermanenceController permanence = createPermanenceController();
                    permanence.backupGame(_info, GAME_SAVE_PATH);
                }
                catch (final IOException e) {
                    // TODO リプレイデータ作成失敗時の処理
                }
                
                _core.start(_info);
            }
        }
    }
    
    
    
    /**
     * 麻雀コントローラを生成
     * 
     * @return 麻雀コントローラ。
     */
    private JanController createJanController() {
        return new VSAIJanController();
    }
    
    /**
     * 永続化コントローラを生成
     * 
     * @return 永続化コントローラ
     */
    private PermanenceController createPermanenceController() {
        return new BinaryPermanenceController();
    }
    
    
    
    /**
     * 保存パス
     */
    private static final String GAME_SAVE_PATH = "./backup.bin";
    
    /**
     * 自分自身のインスタンス
     */
    private static final GameMaster INSTANCE = new GameMaster();
    
    
    
    /**
     * ロックオブジェクト (麻雀コントローラ)
     */
    private final Object _CORE_LOCK = new Object();
    
    /**
     * ロックオブジェクト (ゲームの情報)
     */
    private final Object _INFO_LOCK = new Object();
    
    
    
    /**
     * 麻雀コントローラ
     */
    private JanController _core = createJanController();
    
    /**
     * ゲームの情報
     */
    private GameInfo _info = new GameInfo();
    
}

