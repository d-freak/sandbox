/**
 * GameInfo.java
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.TreeMap;

import wiz.project.jan.player.HumanPlayer;
import wiz.project.jan.player.Player;



/**
 * ゲーム情報
 */
public final class GameInfo extends Observable implements Cloneable {
    
    /**
     * コンストラクタ
     */
    public GameInfo() {
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public GameInfo(final GameInfo source) {
        if (source != null) {
            _state = source._state;
            _playerTable = deepCopyPlayerTable(source._playerTable);
            _deck = deepCopyList(source._deck);
            _drawIndex = source._drawIndex;
            _deadWallIndex = source._deadWallIndex;
            _remainCount = source._remainCount;
            _activePlayerWind = source._activePlayerWind;
            _activeDraw = source._activeDraw;
        }
    }
    
    
    
    /**
     * 情報を全消去
     */
    public void clear() {
        _state = GameState.CLOSE;
        _playerTable.clear();
        _deck.clear();
        _drawIndex = 0;
        _deadWallIndex = 0;
        _remainCount = 0;
        _activePlayerWind = Wind.TON;
        _activeDraw = null;
    }
    
    /**
     * ディープコピーを取得
     * 
     * @return ディープコピー。
     */
    @Override
    public GameInfo clone() {
        return new GameInfo(this);
    }
    
    /**
     * アクティブなツモ牌を取得
     * 
     * @return アクティブなツモ牌。存在しない場合はnullを返す。
     */
    public JanPai getActiveDraw() {
        return _activeDraw;
    }
    
    /**
     * アクティブプレイヤーを取得
     * 
     * @return アクティブプレイヤー。
     */
    public Player getActivePlayer() {
        return getPlayer(_activePlayerWind);
    }
    
    /**
     * アクティブプレイヤーの風を取得
     * 
     * @return アクティブプレイヤーの風。
     */
    public Wind getActivePlayerWind() {
        return _activePlayerWind;
    }
    
    /**
     * 嶺上牌インデックスを取得
     * 
     * @return 嶺上牌インデックス。
     */
    public int getDeadWallIndex() {
        return _deadWallIndex;
    }
    
    /**
     * 牌山を取得
     * 
     * @return 牌山。
     */
    public List<JanPai> getDeck() {
        return deepCopyList(_deck);
    }
    
    /**
     * ツモ牌インデックスを取得
     * 
     * @return ツモ牌インデックス。
     */
    public int getDrawIndex() {
        return _drawIndex;
    }
    
    /**
     * プレイヤーを取得
     * 
     * @param wind 風。
     * @return プレイヤー。
     */
    public Player getPlayer(final Wind wind) {
        if (wind != null) {
            return _playerTable.get(wind).clone();
        }
        else {
            return new HumanPlayer();
        }
    }
    
    /**
     * 残枚数カウントを取得
     * 
     * @return 残枚数カウント。
     */
    public int getRemainCount() {
        return _remainCount;
    }
    
    /**
     * ゲームの状態を取得
     * 
     * @return ゲームの状態。
     */
    public GameState getState() {
        return _state;
    }
    
    /**
     * アクティブなツモ牌を削除
     */
    public void removeActiveDraw() {
        setActiveDraw(null);
    }
    
    /**
     * アクティブなツモ牌を設定
     * 
     * @param pai アクティブなツモ牌。
     */
    public void setActiveDraw(final JanPai pai) {
        _activeDraw = pai;
        setChanged();
    }
    
    /**
     * アクティブプレイヤーの風を設定
     * 
     * @param wind アクティブプレイヤーの風。
     */
    public void setActivePlayerWind(final Wind wind) {
        if (wind != null) {
            _activePlayerWind = wind;
        }
        else {
            _activePlayerWind = Wind.TON;
        }
        setChanged();
    }
    
    /**
     * 嶺上牌インデックスを設定
     * 
     * @param index 嶺上牌インデックス。
     */
    public void setDeadWallIndex(final int index) {
        if (index > 0) {
            _deadWallIndex = index;
        }
        else {
            _deadWallIndex = 0;
        }
        setChanged();
    }
    
    /**
     * 牌山を設定
     * 
     * @param deck 牌山。
     */
    public void setDeck(final List<JanPai> deck) {
        if (deck != null) {
            _deck = deepCopyList(deck);
        }
        else {
            _deck.clear();
        }
        setChanged();
    }
    
    /**
     * ツモ牌インデックスを設定
     * 
     * @param index ツモ牌インデックス。
     */
    public void setDrawIndex(final int index) {
        if (index > 0) {
            _drawIndex = index;
        }
        else {
            _drawIndex = 0;
        }
        setChanged();
    }
    
    /**
     * プレイヤーを設定
     * 
     * @param wind 風。
     * @param player プレイヤー。
     */
    public void setPlayer(final Wind wind, final Player player) {
        if (wind != null) {
            if (player != null) {
                final Player entity = player.clone();
                _playerTable.put(wind, entity);
                addObserver(entity);
            }
            else {
                _playerTable.remove(wind);
            }
            setChanged();
        }
    }
    
    /**
     * 残枚数カウントを設定
     * 
     * @param count 残枚数カウント。
     */
    public void setRemainCount(final int count) {
        if (count > 0) {
            _remainCount = count;
        }
        else {
            _remainCount = 0;
        }
        setChanged();
    }
    
    /**
     * ゲームの状態を設定
     * 
     * @param state ゲームの状態。
     */
    public void setState(final GameState state) {
        if (state != null) {
            _state = state;
        }
        else {
            _state = GameState.CLOSE;
        }
        setChanged();
    }
    
    
    
    /**
     * リストをディープコピー
     * 
     * @param sourceList 複製元。
     * @return 複製結果。
     */
    private <E> List<E> deepCopyList(final List<E> sourceList) {
        return new ArrayList<>(sourceList);
    }
    
    /**
     * ハッシュテーブルをディープコピー
     * 
     * @param sourceTable 複製元。
     * @return 複製結果。
     */
    private Map<Wind, Player> deepCopyPlayerTable(final Map<Wind, Player> sourceTable) {
        final Map<Wind, Player> resultTable = new TreeMap<>();
        for (final Map.Entry<Wind, Player> entry : sourceTable.entrySet()) {
            resultTable.put(entry.getKey(), entry.getValue().clone());
        }
        return resultTable;
    }
    
    
    
    /**
     * ゲームの状態
     */
    private GameState _state = GameState.CLOSE;
    
    /**
     * プレイヤーテーブル
     */
    private Map<Wind, Player> _playerTable = new TreeMap<>();
    
    /**
     * 牌山
     */
    private List<JanPai> _deck = new ArrayList<>();
    
    /**
     * ツモ牌インデックス
     */
    private int _drawIndex = 0;
    
    /**
     * 嶺上牌インデックス
     */
    private int _deadWallIndex = 0;
    
    /**
     * 残枚数カウント
     */
    private int _remainCount = 0;
    
    /**
     * アクティブプレイヤーの風
     */
    private Wind _activePlayerWind = Wind.TON;
    
    /**
     * アクティブなツモ牌
     */
    private JanPai _activeDraw = null;
    
}

