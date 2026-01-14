class SegmentTree:
    def __init__(self, xs):
        # xs: sorted unique x-coordinates
        self.xs = xs
        self.n = len(xs) - 1  # number of elementary segments
        self.cover = [0] * (4 * self.n)
        self.length = [0] * (4 * self.n)

    def push_up(self, idx, l, r):
        if self.cover[idx] > 0:
            # This whole node interval is covered
            self.length[idx] = self.xs[r] - self.xs[l]
        elif l + 1 == r:
            # leaf, no cover
            self.length[idx] = 0
        else:
            # sum children
            self.length[idx] = self.length[idx * 2] + self.length[idx * 2 + 1]

    def update(self, idx, l, r, ql, qr, val):
        # update cover count on interval [ql, qr)
        if ql >= r or qr <= l:
            return
        if ql <= l and r <= qr:
            self.cover[idx] += val
            self.push_up(idx, l, r)
            return
        mid = (l + r) // 2
        self.update(idx * 2, l, mid, ql, qr, val)
        self.update(idx * 2 + 1, mid, r, ql, qr, val)
        self.push_up(idx, l, r)


class Solution:
    def separateSquares(self, squares):
        # Build x-coordinates and y-events (add/remove)
        xs = set()
        events = []  # (y, typ, x1, x2) typ: +1 add, -1 remove
        for x, y, l in squares:
            xs.add(x)
            xs.add(x + l)
            events.append((y, 1, x, x + l))       # add at bottom
            events.append((y + l, -1, x, x + l))  # remove at top

        xs = sorted(xs)
        x_id = {x: i for i, x in enumerate(xs)}

        # Sort events by y (ascending)
        events.sort()

        # 1) Compute total union area by sweeping once
        st = SegmentTree(xs)
        total = 0.0
        prev_y = events[0][0]
        for y, typ, x1, x2 in events:
            dy = y - prev_y
            if dy > 0:
                # add area of previous horizontal strip
                total += dy * st.length[1]
            # apply event
            st.update(1, 0, st.n, x_id[x1], x_id[x2], typ)
            prev_y = y

        half = total / 2.0

        # 2) Sweep again to find minimal y where accumulated >= half
        st = SegmentTree(xs)
        accumulated = 0.0
        prev_y = events[0][0]

        for y, typ, x1, x2 in events:
            dy = y - prev_y
            if dy > 0:
                strip_width = st.length[1]
                strip_area = dy * strip_width
                if accumulated + strip_area >= half:
                    # The answer lies inside this strip [prev_y, y]
                    if strip_width == 0:
                        # No horizontal coverage in this strip; the area doesn't increase here.
                        return float(prev_y)
                    # find exact y: prev_y + delta where delta * strip_width = half - accumulated
                    delta = (half - accumulated) / strip_width
                    return float(prev_y + delta)
                accumulated += strip_area

            # apply current event and move on
            st.update(1, 0, st.n, x_id[x1], x_id[x2], typ)
            prev_y = y

        # If we never returned inside loop, answer is at last y (degenerate)
        return float(prev_y)
